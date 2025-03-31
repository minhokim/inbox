package kr.re.bgp.jpademo.service;

import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceUpdateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
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

    public List<ChargePlace> list() {
        return repository.findAll();
    }


    public List<ChargePlace> findByPlaceName(String placeName) {
        return repository.findByPlaceName(placeName);
    }

    public List<ChargePlace> findByPlaceNameContainingOrderByPlaceName(String placeName) {
        return repository.findByPlaceNameContainingOrderByPlaceName(placeName);
    }


}
