package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.dto.ConnectorDto;
import kr.re.bgp.jpademo.entity.Connector;
import kr.re.bgp.jpademo.service.ConnectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Connector")
public class ConnectorRestlet {
    private final ConnectorService service;

    @PostMapping("/list")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/EnhancedList")
    public ResponseEntity<Object> enhancedList() {
        return ResponseEntity.ok(service.enhancedList());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ConnectorDto dto) {
        Connector connector = service.create(dto);
        URI location = URI.create("/api/Connector/retrieve/"+connector.getId());
        return ResponseEntity.created(location).body(connector);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Connector connector) {
        return ResponseEntity.ok(service.update(connector));
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Object> retrieve(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.retrieve(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
