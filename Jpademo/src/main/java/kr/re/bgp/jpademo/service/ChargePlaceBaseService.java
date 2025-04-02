package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.entity.ChargePlace;
import org.springframework.stereotype.Service;

@Service
public class ChargePlaceBaseService extends BaseEntityService<ChargePlace> {

    protected ChargePlaceBaseService(EntityManager entityManager) {
        super(entityManager);
    }
}
