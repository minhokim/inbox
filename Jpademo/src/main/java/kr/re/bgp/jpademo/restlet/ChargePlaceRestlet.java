package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceRequestDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceResponseDto;
import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.service.ChargePlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ChargePlace")
public class ChargePlaceRestlet {
    private static final String CHARGE_PLACE_API = "/api/ChargePlace";
    private static final String RETRIEVE_API = "/retrieve";
    private final ChargePlaceService service;

    @PostMapping("/list")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/findByPlaceName/{placeName}")
    public ResponseEntity<Object> findByPlaceName(@PathVariable String placeName) {
        return ResponseEntity.ok(service.findByPlaceName(placeName));
    }

    @GetMapping("/findByPlaceNameContaining/{placeName}")
    public ResponseEntity<Object> findByPlaceNameContaining(@PathVariable String placeName) {
        return ResponseEntity.ok(service.findByPlaceNameContaining(placeName));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ChargePlaceRequestDto createDto) {
       ChargePlace chargePlaceEntity = service.create(createDto);
       URI location = retrieveLocation(chargePlaceEntity);
       ChargePlaceResponseDto responseDto = retrieveResponseDto(chargePlaceEntity);

       return ResponseEntity.created(location).body(responseDto);
    }

    private ChargePlaceResponseDto retrieveResponseDto(ChargePlace chargePlaceEntity) {
        return ChargePlaceResponseDto.builder()
                .placeId(chargePlaceEntity.getPlaceId())
                .placeName(chargePlaceEntity.getPlaceName())
                .build();
    }

    private URI retrieveLocation(ChargePlace chargePlace) {
        return URI.create(CHARGE_PLACE_API + RETRIEVE_API + "/" + chargePlace.getPlaceId());
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ChargePlace chargePlace) {
        return ResponseEntity.ok(service.update(chargePlace));
    }

    @GetMapping(RETRIEVE_API + "/" + "{placeId}")
    public ResponseEntity<Object> retrieve(@PathVariable(name = "placeId") Long placeId) {
        return ResponseEntity.ok(service.retrieve(placeId));
    }

}
