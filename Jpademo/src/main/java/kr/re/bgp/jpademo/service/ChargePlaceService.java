package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargePlaceService extends BaseService<ChargePlace> {
    private final ModelMapper modelMapper;
    private final ChargePlaceRepository repository;

    protected ChargePlaceService(EntityManager entityManager,
                                 ModelMapper modelMapper,
                                 ChargePlaceRepository repository) {
        super(entityManager);
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public ResponseDto create(BaseDto dto) {
        return getChargePlaceResponseDto(repository.save(convertObjectToClass(dto, ChargePlace.class)));
    }

    @Override
    public ResponseDto update(BaseDto dto) {
        return getChargePlaceResponseDto(repository.save(convertObjectToClass(dto, ChargePlace.class)));
    }

    @Override
    public ResponseDto retrieve(Long id) {
        return repository.findById(id)
                .map(this::getChargePlaceResponseDto)
                .orElse(null);
    }

    @Override
    public ResponseDto retrieve(String id) {
        return null;
    }

    public void delete(Long placeId) {
        repository.deleteById(placeId);
    }

    private ChargePlaceResponseDto getChargePlaceResponseDto(ChargePlace chargePlace) {
        return convertObjectToClass(chargePlace, ChargePlaceResponseDto.class);
    }

    private <D> D convertObjectToClass(Object source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }


    public List<ChargePlace> findByPlaceName(String placeName) {
        return repository.findByPlaceName(placeName);
    }

    public List<ChargePlace> findByPlaceNameContainingOrderByPlaceName(String placeName) {
        return repository.findByPlaceNameContainingOrderByPlaceName(placeName);
    }



}
