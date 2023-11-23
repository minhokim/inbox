package datetest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


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
        Timestamp c = new Timestamp(System.currentTimeMillis());
        DateTime dt = new DateTime(c.getTime(), DateTimeZone.getDefault());
        System.out.println("c : " + c);
        System.out.println("dt : " + dt);
        System.out.println("Month : " + dt.getMonthOfYear());
        System.out.println("Hour : " + dt.getHourOfDay());

        Timestamp st = new Timestamp(System.currentTimeMillis() - (6750 * 1000));
        System.out.println("st : " + st);

        long diff = c.getTime() - st.getTime();
        System.out.println(((diff / 1000) / 60) / 60 + " h");
        System.out.println(((diff / 1000) / 60) % 60 +  " m");



    }

    @Test
    public void localDateTest() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.monthOfYear().get());
        assertThat(localDate.monthOfYear().get() == 10);
    }

    @Test
    public void currentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(dateFormat.format(new Date()));
    }

    @Test
    public void currentDateTimeSecond() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(dateFormat.format(new Date()));
    }
}
