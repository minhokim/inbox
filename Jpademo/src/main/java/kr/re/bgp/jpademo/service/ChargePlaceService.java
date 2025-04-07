package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChargePlaceService extends BaseService<ChargePlace, ChargePlaceResponseDto> {
    private final ChargePlaceRepository repository;
    protected ChargePlaceService(EntityManager entityManager,
                                 ModelMapper modelMapper,
                                 ChargePlaceRepository repository) {
        super(entityManager, modelMapper);
        this.repository = repository;
    }

    @Override
    public ResponseDto create(BaseDto dto) {
        return convertToDto(repository.save(convertToEntity(dto)));
    }

    @Override
    public ResponseDto update(BaseDto dto) {
        return convertToDto(repository.save(convertToEntity(dto)));
    }

    @Override
    public ResponseDto retrieve(Long id) {
        return convertToDto(repository.findById(id).orElse(new ChargePlace()));
    }

    @Override
    public ResponseDto retrieve(String id) {
        return null;
    }

    private ChargePlace convertToEntity(BaseDto dto) {
        return mapsObjToClass(dto, ChargePlace.class);
    }

    private ResponseDto convertToDto(ChargePlace chargePlace) {
        return mapsObjToClass(chargePlace, ChargePlaceResponseDto.class);
    }

    public Optional<ChargePlace> retrieveChargePlace(Long id) {
        return repository.findById(id);
    }

    public void delete(Long placeId) {
        repository.deleteById(placeId);
    }

    public List<ChargePlace> findByPlaceName(String placeName) {
        return repository.findByPlaceName(placeName);
    }

    public List<ChargePlace> findByPlaceNameContainingOrderByPlaceName(String placeName) {
        return repository.findByPlaceNameContainingOrderByPlaceName(placeName);
    }



}
