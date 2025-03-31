package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceUpdateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.dto.param.SearchCondition;
import kr.re.bgp.jpademo.dto.param.SortCondition;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
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

        Root<ChargePlace> chargePlace = criteriaQuery.from(ChargePlace.class);
        criteriaQuery.select(chargePlace);

        List<Predicate> condition = new ArrayList<>();
        for (SearchCondition search : param.getSearchConditions()) {
            if ("placeName".equals(search.getSearchKey())) {
                condition.add(builder.like(chargePlace.get(search.getSearchKey()), "%"+search.getSearchValue()+"%"));
            } else if ("placeId".equals(search.getSearchKey())) {
                condition.add(builder.equal(chargePlace.get("placeId"), search.getSearchValue()));
            }
        }

        Predicate predicate = builder.and(condition.toArray(new Predicate[condition.size()]));
        criteriaQuery.where(predicate);

//        List<Order> orders = retrieveOrders(param, builder, chargePlace);
        List<Order> orders = new ArrayList<>();
        for (SortCondition sortCondition : param.getSortConditions()) {
            if (!StringUtils.isEmpty(sortCondition.getSortKey()) && !StringUtils.isEmpty(sortCondition.getSortDirection().value())) {
                if ("ASC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.asc(chargePlace.get(sortCondition.getSortKey())));
                } else if ("DESC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.desc(chargePlace.get(sortCondition.getSortKey())));
                }
            }
        }

        Order[] orderArray = orders.toArray(new Order[orders.size()]);

        criteriaQuery.orderBy(orderArray);

        TypedQuery<ChargePlace> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(param.getPage());
        query.setMaxResults(param.getLimit());

        List<ChargePlace> chargePlaces = query.getResultList();
        long total = getTotalCount(builder, criteriaQuery);

        Pageable pageable = PageRequest.of(param.getPage(), param.getLimit());

        return new PageImpl<>(chargePlaces, pageable, chargePlaces.size());
    }

    private List<Order> retrieveOrders(ListParam param, CriteriaBuilder builder, Root<ChargePlace> chargePlace) {
        List<Order> orders = new ArrayList<>();
        for (SortCondition sortCondition : param.getSortConditions()) {
            if (!StringUtils.isEmpty(sortCondition.getSortKey()) && !StringUtils.isEmpty(sortCondition.getSortDirection().value())) {
                if ("ASC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.asc(chargePlace.get(sortCondition.getSortKey())));
                } else if ("DESC".equalsIgnoreCase(sortCondition.getSortDirection().value())) {
                    orders.add(builder.desc(chargePlace.get(sortCondition.getSortKey())));
                }
            }
        }

        return orders;
    }

    private long getTotalCount(CriteriaBuilder builder, CriteriaQuery<ChargePlace> criteriaQuery) {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<ChargePlace> chargePlaceRoot = countQuery.from(ChargePlace.class);
        countQuery.select(builder.count(chargePlaceRoot));
        countQuery.where(criteriaQuery.getRestriction());
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public List<ChargePlace> findByPlaceName(String placeName) {
        return repository.findByPlaceName(placeName);
    }

    public List<ChargePlace> findByPlaceNameContainingOrderByPlaceName(String placeName) {
        return repository.findByPlaceNameContainingOrderByPlaceName(placeName);
    }


}
