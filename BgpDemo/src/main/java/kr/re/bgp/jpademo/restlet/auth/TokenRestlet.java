package kr.re.bgp.jpademo.restlet.auth;

import kr.re.bgp.jpademo.service.auth.TokenProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Auth")
public class TokenRestlet {
    private final TokenProviderService tokenProviderService;

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenProviderService.createToken(authentication);
    }

}
