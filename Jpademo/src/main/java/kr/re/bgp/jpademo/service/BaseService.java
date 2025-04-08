package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.dto.param.SearchCondition;
import kr.re.bgp.jpademo.dto.param.SortCondition;
import kr.re.bgp.jpademo.dto.param.SortDirectionEnum;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService<T, R extends ResponseDto> {
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final Class<T> entityClass;
    private final Class<R> responseDtoClass;

    protected BaseService(EntityManager entityManager, ModelMapper modelMapper) {
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;

        Type superclass = this.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
            this.responseDtoClass = (Class<R>) parameterizedType.getActualTypeArguments()[1];
        } else {
            this.entityClass = null;
            this.responseDtoClass = null;
        }
    }

    /**
     * ResponseDto 목록을 리턴 - API 제공을 위한
     * @param param
     * @return
     */
    public Page<R> list(ListParam param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);

        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        Predicate predicate = paramToPredicate(builder, root, param);
        criteriaQuery.where(predicate);

        Order[] orderArray = paramToOrders(builder, root, param);
        criteriaQuery.orderBy(orderArray);

        int pageNumber = param.getPage() - 1;
        int pageSize = param.getLimit();

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(param.getLimit());

        List<T> resultList = query.getResultList();
        List<R> responseDtoList = resultList.stream()
                .map(entity -> mapsObjToClass(entity, responseDtoClass))
                .collect(Collectors.toList());

        long total = getTotalCount(builder, param, entityClass);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return new PageImpl<>(responseDtoList, pageable, total);
    }

    /**
     * ENTITY 목록을 리턴 - 내부에서 사용하기 위한
     * @param param
     * @return
     */
    public Page<T> findAll(ListParam param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);

        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        Predicate predicate = paramToPredicate(builder, root, param);
        criteriaQuery.where(predicate);

        Order[] orderArray = paramToOrders(builder, root, param);
        criteriaQuery.orderBy(orderArray);

        int pageNumber = param.getPage() - 1;
        int pageSize = param.getLimit();

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(param.getLimit());

        List<T> resultList = query.getResultList();

        long total = getTotalCount(builder, param, entityClass);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return new PageImpl<>(resultList, pageable, total);
    }

    public T findTop(String sortKey, String direction) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);

        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        Order[] orderArray = sortToOrders(builder, root, retrieveSort(sortKey, direction));
        criteriaQuery.orderBy(orderArray);

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(0).setMaxResults(1);

        List<T> resultList = query.getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    private List<SortCondition> retrieveSort(String sortKey, String direction) {
        List<SortCondition> sorts = new ArrayList<>();
        sorts.add(SortCondition.builder()
                .sortKey(sortKey)
                .sortDirection(SortDirectionEnum.valueOf(StringUtils.toRootUpperCase(direction)))
                .build()
        );

        return sorts;
    }

    private Predicate paramToPredicate(CriteriaBuilder builder, Root<T> root, ListParam param) {
        String[] likeKeys = {"NAME"};
        String[] periodKeys = {"STARTDATE", "ENDDATE"};

        List<Predicate> conditions = new ArrayList<>();
        for (SearchCondition search : param.getSearchConditions()) {
            if (StringUtils.isEmpty(search.getSearchKey())) {
                continue;
            }

            if (isContains(search, likeKeys)) {
                conditions.add(builder.like(root.get(search.getSearchKey()), "%" + search.getSearchValue() + "%"));
            } else if (isContains(search, periodKeys)) {
                if (isContains(search, "STARTDATE")) {
                    conditions.add(builder.greaterThanOrEqualTo(root.get("created"),
                            Timestamp.valueOf(search.getSearchValue() + " 00:00:00")));
                } else if (isContains(search, "ENDDATE")) {
                    conditions.add(builder.lessThanOrEqualTo(root.get("created"),
                            Timestamp.valueOf(search.getSearchValue() + " 23:59:59")));
                }
            } else {
                conditions.add(builder.equal(root.get(search.getSearchKey()), search.getSearchValue()));
            }
        }

        return builder.and(conditions.toArray(new Predicate[conditions.size()]));
    }

    private boolean isContains(SearchCondition search, String... searchKey) {
        return Arrays.stream(searchKey)
                .anyMatch(l -> StringUtils.toRootUpperCase(search.getSearchKey()).contains(l));
    }

    private Order[] paramToOrders(CriteriaBuilder builder, Root<T> root, ListParam param) {
        List<Order> orders = new ArrayList<>();
        for (SortCondition sort : param.getSortConditions()) {
            if (isValidSortCondition(sort)) {
                if (isAsc(sort)) {
                    orders.add(builder.asc(root.get(sort.getSortKey())));
                } else if (isDesc(sort)) {
                    orders.add(builder.desc(root.get(sort.getSortKey())));
                }
            }
        }

        return orders.toArray(new Order[orders.size()]);
    }

    private Order[] sortToOrders(CriteriaBuilder builder, Root<T> root, List<SortCondition> sorts) {
        List<Order> orders = new ArrayList<>();
        for (SortCondition sort : sorts) {
            if (isValidSortCondition(sort)) {
                if (isAsc(sort)) {
                    orders.add(builder.asc(root.get(sort.getSortKey())));
                } else if (isDesc(sort)) {
                    orders.add(builder.desc(root.get(sort.getSortKey())));
                }
            }
        }

        return orders.toArray(new Order[orders.size()]);
    }

    private boolean isValidSortCondition(SortCondition sort) {
        return !StringUtils.isEmpty(sort.getSortKey()) && !StringUtils.isEmpty(sort.getSortDirection().value());
    }

    private boolean isAsc(SortCondition sort) {
        return "ASC".equalsIgnoreCase(sort.getSortDirection().value());
    }

    private boolean isDesc(SortCondition sort) {
        return "DESC".equalsIgnoreCase(sort.getSortDirection().value());
    }

    private long getTotalCount(CriteriaBuilder builder, ListParam param, Class<T> destClazz) {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<T> countRoot = countQuery.from(destClazz);
        countQuery.select(builder.count(countRoot));

        Predicate predicate = paramToPredicate(builder, countRoot, param);
        countQuery.where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    protected <D> D mapsObjToClass(Object source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public abstract ResponseDto create(BaseDto dto);

    public abstract ResponseDto update(BaseDto dto);

    public abstract ResponseDto retrieve(Long id);

    public abstract ResponseDto retrieve(String id);

}
