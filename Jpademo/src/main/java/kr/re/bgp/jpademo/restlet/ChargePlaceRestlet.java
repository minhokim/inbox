package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.entity.ChargePlace;
import kr.re.bgp.jpademo.service.ChargePlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ChargePlace")
public class ChargePlaceRestlet {
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
    public ResponseEntity<Object> create(@RequestBody ChargePlace chargePlace) {
        return ResponseEntity.ok(service.create(chargePlace));
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ChargePlace chargePlace) {
        return ResponseEntity.ok(service.update(chargePlace));
    }

    @GetMapping("/retrieve/{placeId}")
    public ResponseEntity<Object> retrieve(@PathVariable(name = "placeId") Long placeId) {
        return ResponseEntity.ok(service.retrieve(placeId));
    }

}
