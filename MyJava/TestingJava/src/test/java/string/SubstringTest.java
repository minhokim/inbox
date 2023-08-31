package string;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SubstringTest {

    @Test
    public void getStr() {
        /*String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }*/

        String bearerToken = "Basic token";
        if (StringUtils.isEmpty(bearerToken)) {
            System.out.println("basicToken : " + "");
        }

        if (bearerToken.startsWith("Basic ")) {
            assertThat(bearerToken.substring(6)).isEqualTo("token");
        }

    }


}
