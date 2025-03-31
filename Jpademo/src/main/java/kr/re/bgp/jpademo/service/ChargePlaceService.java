package kr.re.bgp.jpademo.service;

import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceRequestDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ChargePlaceService {
    private final ModelMapper modelMapper;
    private final ChargePlaceRepository repository;

    public ChargePlaceResponseDto create(ChargePlaceCreateDto dto) {
        return retrieveResponseDto(repository.save(convertObjectToClass(dto, ChargePlace.class)));
    }

    public ChargePlaceResponseDto update(ChargePlaceRequestDto dto) {
        ChargePlace chargePlace = repository.save(convertObjectToClass(dto, ChargePlace.class));
        return retrieveResponseDto(chargePlace);
    }

    private ChargePlaceResponseDto retrieveResponseDto(ChargePlace chargePlace) {
        return convertObjectToClass(chargePlace, ChargePlaceResponseDto.class);
    }

    private <D> D convertObjectToClass(Object source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public ChargePlace retrieve(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ChargePlaceResponseDto retrieveResponseDto(Long id) {
        Optional<ChargePlace> chargePlace = repository.findById(id);
        return chargePlace.map(this::retrieveResponseDto).orElse(null);
    }

    public Long delete(Long id) {
        repository.deleteById(id);
        return id;
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
