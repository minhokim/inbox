package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceUpdateDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.dto.param.SearchCondition;
import kr.re.bgp.jpademo.dto.param.SortCondition;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChargePlaceService {
    private final ModelMapper modelMapper;
    private final ChargePlaceRepository repository;
    private final EntityManager entityManager;

    public ChargePlaceResponseDto create(ChargePlaceCreateDto dto) {
        return getChargePlaceResponseDto(repository.save(convertObjectToClass(dto, ChargePlace.class)));
    }

    public ChargePlaceResponseDto update(ChargePlaceUpdateDto dto) {
        return getChargePlaceResponseDto(repository.save(convertObjectToClass(dto, ChargePlace.class)));
    }

    private ChargePlaceResponseDto getChargePlaceResponseDto(ChargePlace chargePlace) {
        return convertObjectToClass(chargePlace, ChargePlaceResponseDto.class);
    }

    private <D> D convertObjectToClass(Object source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public ChargePlace retrieveChargePlace(Long placeId) {
        return repository.findById(placeId).orElse(null);
    }

    public ChargePlaceResponseDto retrieve(Long placeId) {
        return repository.findById(placeId)
                .map(this::getChargePlaceResponseDto)
                .orElse(null);
    }

    public void delete(Long placeId) {
        repository.deleteById(placeId);
    }



    public Page<ChargePlace> listByCriteria(ListParam param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChargePlace> criteriaQuery = builder.createQuery(ChargePlace.class);

        Root<ChargePlace> chargePlaceRoot = criteriaQuery.from(ChargePlace.class);
        criteriaQuery.select(chargePlaceRoot);

        Predicate predicate = paramToPredicate(builder, chargePlaceRoot, param);
        criteriaQuery.where(predicate);

        Order[] orderArray = paramToOrders(builder, chargePlaceRoot, param);
        criteriaQuery.orderBy(orderArray);

        int pageNumber = param.getPage() - 1;
        int pageSize = param.getLimit();

        TypedQuery<ChargePlace> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(param.getLimit());
        List<ChargePlace> chargePlaces = query.getResultList();

        long total = getTotalCount(builder, param);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return new PageImpl<>(chargePlaces, pageable, total);
    }

    private Predicate paramToPredicate(CriteriaBuilder builder, Root<ChargePlace> root, ListParam param) {
        List<Predicate> conditions = new ArrayList<>();
        for (SearchCondition search : param.getSearchConditions()) {
            if ("placeName".equals(search.getSearchKey())) {
                conditions.add(builder.like(root.get(search.getSearchKey()), "%"+search.getSearchValue()+"%"));
            } else if ("placeId".equals(search.getSearchKey())) {
                conditions.add(builder.equal(root.get("placeId"), search.getSearchValue()));
            }
        }

        return builder.and(conditions.toArray(new Predicate[conditions.size()]));
    }

    private Order[] paramToOrders(CriteriaBuilder builder, Root<ChargePlace> chargePlaceRoot, ListParam param) {
        List<Order> orders = new ArrayList<>();
        for (SortCondition sortCondition : param.getSortConditions()) {
            if (!StringUtils.isEmpty(sortCondition.getSortKey()) && !StringUtils.isEmpty(sortCondition.getSortDirection().value())) {
                if ("ASC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.asc(chargePlaceRoot.get(sortCondition.getSortKey())));
                } else if ("DESC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.desc(chargePlaceRoot.get(sortCondition.getSortKey())));
                }
            }
        }

        return orders.toArray(new Order[orders.size()]);
    }

    private long getTotalCount(CriteriaBuilder builder, ListParam param) {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<ChargePlace> countRoot = countQuery.from(ChargePlace.class);
        countQuery.select(builder.count(countRoot));

        Predicate predicate = paramToPredicate(builder, countRoot, param);
        countQuery.where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public List<ChargePlace> findByPlaceName(String placeName) {
        return repository.findByPlaceName(placeName);
    }

    public List<ChargePlace> findByPlaceNameContainingOrderByPlaceName(String placeName) {
        return repository.findByPlaceNameContainingOrderByPlaceName(placeName);
    }


}
