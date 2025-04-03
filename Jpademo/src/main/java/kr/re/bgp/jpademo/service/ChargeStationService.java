package kr.re.bgp.jpademo.service;

import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.entity.ChargeStation;
import kr.re.bgp.jpademo.repository.ChargeStationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChargeStationService {
    private final ChargePlaceService chargePlaceService;
    private final ChargeStationRepository chargeStationrepository;

    public ChargeStation create(ChargeStation chargeStation) {
        ChargePlace chargePlace = chargePlaceService.retrieveChargePlace(chargeStation.getPlaceId());
        chargeStation.setPlace(chargePlace);
        return chargeStationrepository.save(chargeStation);
    }

    public ChargeStation update(ChargeStation chargeStation) {
        return chargeStationrepository.save(chargeStation);
    }

    public ChargeStation retrieve(Long id) {
        return chargeStationrepository.findById(id).orElse(null);
    }

    public List<ChargeStation> list() {
        return chargeStationrepository.findAll();
    }
}
