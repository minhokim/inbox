package etc;

import org.junit.jupiter.api.Test;

public class EtcTest {
    @Test
    public void etcTest() {
        float watt = 104.799995f;
        System.out.println(Math.round(watt * 100) / 100.0);
    }
}
