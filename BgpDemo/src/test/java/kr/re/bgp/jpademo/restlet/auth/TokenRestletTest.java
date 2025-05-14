package kr.re.bgp.jpademo.restlet.auth;

import kr.re.bgp.jpademo.config.SecurityConfig;
import kr.re.bgp.jpademo.service.auth.CustomUserDetailsService;
import kr.re.bgp.jpademo.service.auth.TokenProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TokenRestlet.class)
@Import({SecurityConfig.class, TokenProviderService.class})
public class TokenRestletTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;


    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
        // @formatter:off
        MvcResult result = this.mockMvc.perform(post("/Auth/token")
                        .with(httpBasic("bgp@re.kr", "bgp")))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        this.mockMvc.perform(get("/Auth/hello")
                        .header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hello, user!"));
        // @formatter:on
    }

    @Test
    void tokenWhenUnauthenticatedThen401() throws Exception {
        this.mockMvc.perform(post("/Auth/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void tokenWhenBadCredentialsThen401() throws Exception {
        this.mockMvc.perform(post("/Auth/token"))
                .andExpect(status().isUnauthorized());
    }


}