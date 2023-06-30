package etc;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class EtcTest {
    @Test
    public void etcTest() {
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
