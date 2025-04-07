package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.chargestation.ChargeStationCreateDto;
import kr.re.bgp.jpademo.dto.chargestation.ChargeStationResponseDto;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.entity.ChargeStation;
import kr.re.bgp.jpademo.repository.ChargeStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChargeStationService extends BaseService<ChargeStation, ChargeStationResponseDto>{
    private final ChargePlaceService chargePlaceService;
    private final ChargeStationRepository repository;

    public ChargeStationService(EntityManager entityManager,
                                ModelMapper modelMapper,
                                ChargeStationRepository repository,
                                ChargePlaceService chargePlaceService
    ) {
        super(entityManager, modelMapper);
        this.chargePlaceService = chargePlaceService;
        this.repository = repository;
    }

    @Override
    public ResponseDto create(BaseDto dto) {
        ChargePlace chargePlace = retrieveChargePlace(dto);

        ChargeStation chargeStation = convertToEntity(dto);
        chargeStation.setPlace(chargePlace);

        return convertToDto(repository.save(chargeStation));
    }

    private ChargePlace retrieveChargePlace(BaseDto dto) {
        ChargeStationCreateDto createDto = (ChargeStationCreateDto) dto;
        return chargePlaceService.retrieveChargePlace(createDto.getPlaceId()).orElse(null);
    }

    @Override
    public ResponseDto update(BaseDto dto) {
        ChargePlace chargePlace = retrieveChargePlace(dto);

        ChargeStation chargeStation = convertToEntity(dto);
        chargeStation.setPlace(chargePlace);

        return convertToDto(repository.save(chargeStation));
    }

    private ChargeStation convertToEntity(BaseDto dto) {
        return mapsObjToClass(dto, ChargeStation.class);
    }

    private ResponseDto convertToDto(ChargeStation chargeStation) {
        return mapsObjToClass(chargeStation, ChargeStationResponseDto.class);
    }

    @Override
    public ResponseDto retrieve(Long id) {
        return convertToDto(repository.findById(id).orElse(new ChargeStation()));
    }

    public ChargeStation retrieveEntity(Long id) {
        return repository.findById(id).orElse(new ChargeStation());
    }

    @Override
    public ResponseDto retrieve(String id) {
        return null;
    }

    public void delete(Long stationId) {
        repository.deleteById(stationId);
    }
}
