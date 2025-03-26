package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.entity.ChargeStation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriteriaBuilderService {
    private final EntityManager entityManager;

    public List<Object[]> chargePlacesExam() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        Root<ChargePlace> chargePlace = criteriaQuery.from(ChargePlace.class);

        criteriaQuery.multiselect(
                chargePlace.get("placeId"),
                chargePlace.get("placeName"),
                chargePlace.get("created"),
                chargePlace.get("stations").get("stationName")
        );

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        List<Object[]> chargePlaces = query.getResultList();

        return chargePlaces;
    }

    public List<ChargePlace> chargePlaces() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChargePlace> criteriaQuery = builder.createQuery(ChargePlace.class);

        Root<ChargePlace> place = criteriaQuery.from(ChargePlace.class);

        place.fetch("stations", JoinType.INNER);

        criteriaQuery.select(place);

        TypedQuery<ChargePlace> query = entityManager.createQuery(criteriaQuery);
        List<ChargePlace> chargePlaces = query.getResultList();

        for (ChargePlace chargePlace : chargePlaces) {
            System.out.println(chargePlace.toString());

            for (ChargeStation station : chargePlace.getStations()) {
                System.out.println(station.getStationName());
            }
        }

        return chargePlaces;
    }

    public List<ChargeStation> chargeStations() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChargeStation> criteriaQuery = builder.createQuery(ChargeStation.class);
        criteriaQuery.select(criteriaQuery.from(ChargeStation.class));
        TypedQuery<ChargeStation> query = entityManager.createQuery(criteriaQuery);
        List<ChargeStation> chargeStations = query.getResultList();

        return chargeStations;
    }


}
