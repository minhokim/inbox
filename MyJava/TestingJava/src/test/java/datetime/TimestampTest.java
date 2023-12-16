package datetime;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class TimestampTest {

    @Test
    public void timestampTest() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
}
