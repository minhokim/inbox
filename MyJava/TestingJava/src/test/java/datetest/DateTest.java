package datetest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    @Test
    public void strDateToTimestamp() {
        String str = "2023-07-03 14:00:00";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        System.out.println(timestamp);
    }

    @Test
    public void afterFiveYears() {
        DateTime dateTime = new DateTime().plusYears(5);
        System.out.println(dateTime.toDateTime());
        System.out.println(new Timestamp(dateTime.toDateTime().getMillis()));
    }

    @Test
    public void timestamp() {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        DateTime dt = new DateTime(t.getTime(), DateTimeZone.getDefault());
        System.out.println("t : " + t);
        System.out.println("dt : " + dt);
        System.out.println("Month : " + dt.getMonthOfYear());
        System.out.println("Hour : " + dt.getHourOfDay());
    }
}
