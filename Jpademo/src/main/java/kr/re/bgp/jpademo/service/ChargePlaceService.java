package kr.re.bgp.jpademo.service;

import jakarta.persistence.EntityManager;
import kr.re.bgp.jpademo.dto.BaseDto;
import kr.re.bgp.jpademo.dto.ResponseDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.dto.param.SearchCondition;
import kr.re.bgp.jpademo.dto.param.SortCondition;
import kr.re.bgp.jpademo.dto.param.SortDirectionEnum;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.repository.ChargePlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChargePlaceService extends BaseService<ChargePlace, ChargePlaceResponseDto> {
    private final ChargePlaceRepository repository;
    protected ChargePlaceService(EntityManager entityManager,
                                 ModelMapper modelMapper,
                                 ChargePlaceRepository repository) {
        super(entityManager, modelMapper);
        this.repository = repository;
    }

    public ChargePlace findTop(String sortKey, String direction) {
        return super.findTop(sortKey, direction);
    }

    public Page<ChargePlace> findByConditions() {
        ListParam listParam = retrieveListParam(retrieveSearches(), retrieveSorts());

        return findAll(listParam);
    }

    private List<SearchCondition> retrieveSearches() {
        List<SearchCondition> searches = new ArrayList<>();
        searches.add(
                SearchCondition.builder()
                        .searchKey("placeName")
                        .searchValue("오늘")
                        .build()
        );

        return searches;
    }

    private List<SortCondition> retrieveSorts() {
        List<SortCondition> sorts = new ArrayList<>();
        sorts.add(SortCondition.builder()
                .sortKey("placeName")
                .sortDirection(SortDirectionEnum.ASC)
                .build()
        );

        return sorts;
    }

    private ListParam retrieveListParam(List<SearchCondition> searches, List<SortCondition> sorts) {
        ListParam param = new ListParam();
        param.withPage(1)
                .withLimit(100)
                .withSearchConditions(searches)
                .withSortConditions(sorts);

        return param;
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
