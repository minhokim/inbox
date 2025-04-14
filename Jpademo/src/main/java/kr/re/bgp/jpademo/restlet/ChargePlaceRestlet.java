package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.config.ApiError;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceUpdateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.service.ChargePlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ChargePlace")
public class ChargePlaceRestlet {
    private static final String FIND_TOP_PATH = "/FindTop";
    private static final String FIND_ALL_PATH = "/FindAll";
    private static final String LIST_PATH = "/List";
    private static final String LIST_MYBATIS_PATH = "/ListFromMyBatis";
    private static final String CREATE_PATH = "/Create";
    private static final String UPDATE_PATH = "/Update";
    private static final String DELETE_PATH = "/Delete/{placeId}";
    private static final String RETRIEVE_PATH = "/Retrieve/{placeId}";

    private final ChargePlaceService service;

    @GetMapping(FIND_TOP_PATH)
    public ResponseEntity<Object> findTop(String sortKey, String direction) {
        return ResponseEntity.ok(Map.of("content", service.findTop(sortKey, direction)));
    }

    @PostMapping(FIND_ALL_PATH)
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(service.findByConditions());
    }

    @PostMapping(LIST_PATH)
    public ResponseEntity<Object> list(@RequestBody ListParam param) {
        return ResponseEntity.ok(service.list(param));
    }

    @PostMapping(LIST_MYBATIS_PATH)
    public ResponseEntity<Object> listMyBatis(@RequestBody ListParam param) {
        return ResponseEntity.ok(service.listMyBatis(param));
    }

    @PostMapping(CREATE_PATH)
    public ResponseEntity<Object> create(@RequestBody ChargePlaceCreateDto dto) {
        return ResponseEntity.ok(Map.of("content", service.create(dto)));
    }

    @PutMapping(UPDATE_PATH)
    public ResponseEntity<Object> update(@RequestBody ChargePlaceUpdateDto dto) {
        return ResponseEntity.ok(Map.of("content", service.update(dto)));
    }

    @PatchMapping(UPDATE_PATH)
    public ResponseEntity<Object> patch(@RequestBody ChargePlaceUpdateDto dto) {
        return ResponseEntity.ok(Map.of("content", service.update(dto)));
    }

    @GetMapping(RETRIEVE_PATH)
    public ResponseEntity<Object> retrieve(@PathVariable(name = "placeId") Long placeId) {
        ChargePlaceResponseDto dto = (ChargePlaceResponseDto) service.retrieve(placeId);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        }

        return ResponseEntity.ok(Map.of("content", dto));
    }

    @DeleteMapping(DELETE_PATH)
    public ResponseEntity<Object> delete(@PathVariable(name = "placeId") Long placeId) {

        if (service.retrieve(placeId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        }

        service.delete(placeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByPlaceName/{placeName}")
    public ResponseEntity<Object> findByPlaceName(@PathVariable String placeName) {
        return ResponseEntity.ok(service.findByPlaceName(placeName));
    }

    @GetMapping("/findByPlaceNameContainingOrderByPlaceName/{placeName}")
    public ResponseEntity<Object> findByPlaceNameContainingOrderByPlaceName(@PathVariable String placeName) {
        return ResponseEntity.ok(service.findByPlaceNameContainingOrderByPlaceName(placeName));
    }

}
