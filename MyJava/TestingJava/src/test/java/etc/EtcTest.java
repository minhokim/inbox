package etc;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

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
        System.out.println(dateTime.dayOfWeek().getAsString());
    }


}
