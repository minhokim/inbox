package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.config.ApiError;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceRequestDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.service.ChargePlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ChargePlace")
public class ChargePlaceRestlet {
    private static final String LIST_PATH = "/List";
    private static final String CREATE_PATH = "/Create";
    private static final String UPDATE_PATH = "/Update";
    private static final String DELETE_PATH = "/Delete/{placeId}";
    private static final String RETRIEVE_PATH = "/Retrieve/{placeId}";
    private final ChargePlaceService service;

    @PostMapping(LIST_PATH)
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping(CREATE_PATH)
    public ResponseEntity<Object> create(@RequestBody ChargePlaceCreateDto dto) {
        Map<String, Object> res = new HashMap<>();
        res.put("item", service.create(dto));
        return ResponseEntity.ok(res);
    }

    @PutMapping(UPDATE_PATH)
    public ResponseEntity<Object> update(@RequestBody ChargePlaceRequestDto dto) {
        Map<String, Object> res = new HashMap<>();
        res.put("item", service.update(dto));
        return ResponseEntity.ok(res);
    }

    @GetMapping(RETRIEVE_PATH)
    public ResponseEntity<Object> retrieve(@PathVariable(name = "placeId") Long placeId) {
        ChargePlaceResponseDto dto = retrieveChargePlace(placeId);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        }

        Map<String, Object> res = new HashMap<>();
        res.put("item", dto);
        return ResponseEntity.ok(res);
    }

    private ChargePlaceResponseDto retrieveChargePlace(Long placeId) {
        return service.retrieveResponseDto(placeId);
    }

    @DeleteMapping(DELETE_PATH)
    public ResponseEntity<Object> delete(@PathVariable(name = "placeId") Long placeId) {
        ChargePlaceResponseDto dto = retrieveChargePlace(placeId);

        if (dto == null) {
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
