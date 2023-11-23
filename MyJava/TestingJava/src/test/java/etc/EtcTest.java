package etc;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EtcTest {

    @Test
    public void randomStr() {
        int min = 111111;
        int max = 999999;

        int randomStr = (int) ((Math.random() * (max - min)) + min);
        System.out.println(Integer.valueOf(randomStr));
    }
    @Test
    public void roundTest() {
        float watt = 104.799995f;
        System.out.println(Math.round(watt * 100) / 100.0);
    }

    @Test
    public void dayOfWeek() {
        DateTime dateTime = new DateTime(System.currentTimeMillis());

        System.out.println(System.currentTimeMillis());
        System.out.println(dateTime);
        System.out.println(new DateTime(System.currentTimeMillis()).withZone(DateTimeZone.forID("Asia/Seoul")));
        System.out.println(new DateTime(System.currentTimeMillis()));
        System.out.println(dateTime.dayOfWeek().getAsString());
    }

    @Test
    public void mapTest() {
        Map<String, Object> map = null;
        if (map.isEmpty()) {
            System.out.println("empty");
        }
    }

    @Test
    public void emptyTest() {
        String str = null;
        if (StringUtils.isEmpty(str)) {
            System.out.println("empty");
        }
    }

    @Test
    public void doubleTest() {
        int startMeter = 21250;
        int stopMeter = 21270;
        double energyKw = (stopMeter - startMeter) / 1000d;
        int chargeAmount = (int) (energyKw * 220);
        System.out.println(energyKw);
    }


}
