package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.dto.param.SearchCondition;
import kr.re.bgp.jpademo.dto.param.SortCondition;
import kr.re.bgp.jpademo.entity.ChargePlace;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T> {
    private final EntityManager entityManager;
    private final Class<T> entityClass;

    protected BaseService(EntityManager entityManager) {
        this.entityManager = entityManager;

        Type superclass = this.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        } else {
            this.entityClass = null;
        }
    }

    public Page<T> list(ListParam param) {
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

    private Predicate paramToPredicate(CriteriaBuilder builder, Root<T> root, ListParam param) {
        List<Predicate> conditions = new ArrayList<>();
        for (SearchCondition search : param.getSearchConditions()) {
            if (StringUtils.isEmpty(search.getSearchKey())) {
                continue;
            }
            if ((StringUtils.toRootUpperCase(search.getSearchKey())).contains("NAME")) {
                conditions.add(builder.like(root.get(search.getSearchKey()), "%"+search.getSearchValue()+"%"));
            } else {
                conditions.add(builder.equal(root.get(search.getSearchKey()), search.getSearchValue()));
            }
        }

        return builder.and(conditions.toArray(new Predicate[conditions.size()]));
    }

    private Order[] paramToOrders(CriteriaBuilder builder, Root<T> root, ListParam param) {
        List<Order> orders = new ArrayList<>();
        for (SortCondition sortCondition : param.getSortConditions()) {
            if (!StringUtils.isEmpty(sortCondition.getSortKey()) && !StringUtils.isEmpty(sortCondition.getSortDirection().value())) {
                if ("ASC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.asc(root.get(sortCondition.getSortKey())));
                } else if ("DESC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.desc(root.get(sortCondition.getSortKey())));
                }
            }
        }

        return orders.toArray(new Order[orders.size()]);
    }

    private long getTotalCount(CriteriaBuilder builder, ListParam param, Class<T> destClazz) {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<T> countRoot = countQuery.from(destClazz);
        countQuery.select(builder.count(countRoot));

        Predicate predicate = paramToPredicate(builder, countRoot, param);
        countQuery.where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public abstract ResponseDto create(BaseDto dto);

    public abstract ResponseDto update(BaseDto dto);

    public abstract ResponseDto retrieve(Long id);

    public abstract ResponseDto retrieve(String id);

}
