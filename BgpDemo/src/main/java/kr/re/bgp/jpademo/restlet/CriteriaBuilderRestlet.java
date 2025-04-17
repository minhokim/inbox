package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.service.CriteriaBuilderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/CriteriaBuilder")
public class CriteriaBuilderRestlet {
    private final CriteriaBuilderService service;

    @GetMapping("/ChargePlace/list")
    public ResponseEntity<Object> chargePlaceList() {
        return ResponseEntity.ok(service.chargePlaces());
    }
    @GetMapping("/ChargeStation/list")
    public ResponseEntity<Object> chargeStationList() {
        return ResponseEntity.ok(service.chargeStations());
    }
}
