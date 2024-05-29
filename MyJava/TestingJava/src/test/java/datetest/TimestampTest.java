package datetest;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampTest {

    @Test
    public void compareTimestamp() {
        String str = "2024-04-03 17:39:37";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Timestamp heartBeatTs = new java.sql.Timestamp(parsedDate.getTime());
        System.out.println("heartBeatTs " + heartBeatTs);

        Timestamp current = new Timestamp(System.currentTimeMillis());
        System.out.println("current " + current);

        int comp = current.compareTo(heartBeatTs);
        System.out.println("comp : " + comp);

        System.out.println(current.toInstant());
    }
}
