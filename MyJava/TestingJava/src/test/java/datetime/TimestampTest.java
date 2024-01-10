package datetime;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampTest {

    @Test
    public void timestampTest() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }

    @Test
    public void timestamptoStr() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timestampStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        System.out.println("Timestamp : " + timestamp);
        System.out.println("TimestampStr : " + timestampStr);
    }
}
