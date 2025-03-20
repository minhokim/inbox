package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.entity.ChargeStation;
import kr.re.bgp.jpademo.service.ChargeStationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ChargeStation")
public class ChargeStationRestlet {
   private final ChargeStationService service;

    @PostMapping("/list")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ChargeStation chargeStation) {
        return ResponseEntity.ok(service.create(chargeStation));
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ChargeStation chargeStation) {
        return ResponseEntity.ok(service.update(chargeStation));
    }

    @GetMapping("/retrieve/{stationId}")
    public ResponseEntity<Object> retrieve(@PathVariable(name = "stationId") Long stationId) {
        return ResponseEntity.ok(service.retrieve(stationId));
    }

}
