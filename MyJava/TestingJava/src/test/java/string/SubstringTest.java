package string;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

        String mobile = "01011112222";
        System.out.println(mobile.substring(mobile.length() - 4));

        String ch = "Response:STA_CH_001";
        System.out.println("ch : "+ch.substring(ch.indexOf(":")+1));

        String code = "XH09000000000000";
        System.out.println("code : " + code.substring(0, 4));

        String pId = "IO1000002345";
        System.out.println("pId : " + pId.substring(2));
    }

    @Test
    public void prevUnit() {
        String str = "3kW";
        System.out.println(str.toLowerCase());
        System.out.println(str.toLowerCase().indexOf("kw"));
        System.out.println(str.substring(0, str.toLowerCase().indexOf("kw")));
    }

    @Test
    public void containsList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        System.out.println(list.contains("aa"));
    }

    @Test
    public void endWith() {
        String str = "abc@gmail.com::csms";
        System.out.println(str.endsWith("::csms"));
        System.out.println(str.split("::")[0]);
    }

    @Test
    public void getDay() {
        String str = "2025-01-03";
        String resultStr = str.substring(str.length() - 2, str.length());
        int day = Integer.parseInt(resultStr);
        System.out.println(day);
    }

}
