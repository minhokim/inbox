package kr.re.bgp.jpademo.service;

import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceRequestDto;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChargePlaceService {
    private final ModelMapper modelMapper;
    private final ChargePlaceRepository repository;

    public ChargePlace create(ChargePlaceRequestDto requestDto) {
        ChargePlace chargePlaceEntity = modelMapper.map(requestDto, ChargePlace.class);

        return repository.save(chargePlaceEntity);
    }

    public ChargePlace update(ChargePlace chargePlace) {
        return repository.save(chargePlace);
    }

    public ChargePlace retrieve(Long id) {
        ChargePlace chargePlace = repository.findById(id).orElse(null);

        return chargePlace;
    }

    public List<ChargePlace> list() {
        return repository.findAll();
    }

    public List<ChargePlace> findByPlaceName(String placeName) {
        return repository.findByPlaceName(placeName);
    }

    public List<ChargePlace> findByPlaceNameContaining(String placeName) {
        return repository.findByPlaceNameContaining(placeName);
    }


}
