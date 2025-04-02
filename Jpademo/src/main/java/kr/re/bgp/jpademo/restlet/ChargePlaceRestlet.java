package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.config.ApiError;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceUpdateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.service.ChargePlaceBaseService;
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
    private static final String BASE_LIST_PATH = "/BaseList";
    private static final String LIST_PATH = "/List";
    private static final String CREATE_PATH = "/Create";
    private static final String UPDATE_PATH = "/Update";
    private static final String DELETE_PATH = "/Delete/{placeId}";
    private static final String RETRIEVE_PATH = "/Retrieve/{placeId}";

    private final ChargePlaceService service;
    private final ChargePlaceBaseService baseService;

    @PostMapping(BASE_LIST_PATH)
    public ResponseEntity<Object> baseList(@RequestBody ListParam param) {
        return ResponseEntity.ok(baseService.list(param));
    }

    @PostMapping(LIST_PATH)
    public ResponseEntity<Object> list(@RequestBody ListParam param) {
        return ResponseEntity.ok(service.listByCriteria(param));
    }

    @PostMapping(CREATE_PATH)
    public ResponseEntity<Object> create(@RequestBody ChargePlaceCreateDto dto) {
        return ResponseEntity.ok(Map.of("item", service.create(dto)));
    }

    @PutMapping(UPDATE_PATH)
    public ResponseEntity<Object> update(@RequestBody ChargePlaceUpdateDto dto) {
        return ResponseEntity.ok(Map.of("item", service.update(dto)));
    }

    @GetMapping(RETRIEVE_PATH)
    public ResponseEntity<Object> retrieve(@PathVariable(name = "placeId") Long placeId) {
        ChargePlaceResponseDto dto = service.retrieve(placeId);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        }

        return ResponseEntity.ok(Map.of("item", dto));
    }

    @DeleteMapping(DELETE_PATH)
    public ResponseEntity<Object> delete(@PathVariable(name = "placeId") Long placeId) {

        if (service.retrieveChargePlace(placeId) == null) {
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
