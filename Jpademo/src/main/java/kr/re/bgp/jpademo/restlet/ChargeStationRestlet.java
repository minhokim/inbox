package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.config.ApiError;
import kr.re.bgp.jpademo.dto.chargestation.ChargeStationCreateDto;
import kr.re.bgp.jpademo.dto.chargestation.ChargeStationUpdateDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.entity.ChargeStation;
import kr.re.bgp.jpademo.service.ChargeStationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ChargeStation")
public class ChargeStationRestlet {
    private static final String LIST_PATH = "/List";
    private static final String CREATE_PATH = "/Create";
    private static final String UPDATE_PATH = "/Update";
    private static final String DELETE_PATH = "/Delete/{stationId}";
    private static final String RETRIEVE_PATH = "/Retrieve/{stationId}";

   private final ChargeStationService service;

    @PostMapping(LIST_PATH)
    public ResponseEntity<Object> list(@RequestBody ListParam param) {
        return ResponseEntity.ok(service.list(param));
    }

    @PostMapping(CREATE_PATH)
    public ResponseEntity<Object> create(@RequestBody ChargeStationCreateDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(UPDATE_PATH)
    public ResponseEntity<Object> update(@RequestBody ChargeStationUpdateDto dto) {
        return ResponseEntity.ok(Map.of("content", service.update(dto)));
    }

    @PatchMapping(UPDATE_PATH)
    public ResponseEntity<Object> patch(@RequestBody ChargeStationUpdateDto dto) {
        return ResponseEntity.ok(Map.of("content", service.update(dto)));
    }

    @GetMapping(RETRIEVE_PATH)
    public ResponseEntity<Object> retrieve(@PathVariable(name = "stationId") Long stationId) {
        return ResponseEntity.ok(service.retrieve(stationId));
    }

    @DeleteMapping(DELETE_PATH)
    public ResponseEntity<Object> delete(@PathVariable(name = "stationId") Long placeId) {

        if (service.retrieve(placeId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
        }

        service.delete(placeId);
        return ResponseEntity.noContent().build();
    }

}
