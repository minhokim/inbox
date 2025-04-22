package kr.re.bgp.jpademo.restlet;

import kr.re.bgp.jpademo.dto.account.AccountCreateDto;
import kr.re.bgp.jpademo.dto.chargeplace.ChargePlaceCreateDto;
import kr.re.bgp.jpademo.dto.param.ListParam;
import kr.re.bgp.jpademo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Account")
public class AccountRestlet {
    private static final String FIND_TOP_PATH = "/FindTop";
    private static final String FIND_ALL_PATH = "/FindAll";
    private static final String LIST_PATH = "/List";
    private static final String LIST_MYBATIS_PATH = "/ListFromMyBatis";
    private static final String CREATE_PATH = "/Create";
    private static final String UPDATE_PATH = "/Update";
    private static final String DELETE_PATH = "/Delete/{accountId}";
    private static final String RETRIEVE_PATH = "/Retrieve/{accountId}";

    private final AccountService service;

    @PostMapping(LIST_PATH)
    public ResponseEntity<Object> list(@RequestBody ListParam param) {
        return ResponseEntity.ok(service.list(param));
    }

    @PostMapping(CREATE_PATH)
    public ResponseEntity<Object> create(@RequestBody AccountCreateDto dto) {
        return ResponseEntity.ok(Map.of("content", service.create(dto)));
    }



}
