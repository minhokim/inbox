package datetime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class DatetimeTest {

    @Test
    public void timezoneDatetime() {

        System.out.println("--");
    }

    @Test
    public void instant() {
        Instant current = Instant.now();
        System.out.println("Current Instant : " + current);

        long epochSecond = current.getEpochSecond();
        System.out.println("Current Timestamp in seconds : " + epochSecond);

        long epochMilli = current.toEpochMilli();
        System.out.println("Current Timestamp in milli seconds : " + epochMilli);

        Instant worldCup = Instant.ofEpochSecond(1024399800);
        System.out.println("2002 World Cup : " + worldCup);
        System.out.println("10 Seconds Later : " + worldCup.plusSeconds(10));

        Instant now = Instant.now();
        System.out.println("Now : " + now);
        System.out.println("Is 2002 World Cup before now? " + worldCup.isBefore(now));
        System.out.println("Is 2002 World Cup after now? " + worldCup.isAfter(now));
    }

    @Test
    public void instantAndZonedDateTime() {
        ZonedDateTime zonedDateTimeSeoul = Year.of(2023).atMonth(5).atDay(17).atTime(12, 05).atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("Time in Seoul : " + zonedDateTimeSeoul);

        Instant instant = zonedDateTimeSeoul.toInstant();
        System.out.println("Instant : " + instant + ", Timestamp : " + instant.getEpochSecond());

        ZonedDateTime zonedDateTimeVancouver = instant.atZone(ZoneId.of("America/Vancouver"));
        System.out.println("Time in Vancouver : " + zonedDateTimeVancouver);

    }

    @Test
    public void myTest() {
        String strDate = "2023-05-17 14:51:05";
        Timestamp timestamp = Timestamp.valueOf(strDate);
        System.out.println("Timestamp : " + timestamp);

        ZonedDateTime zdDtSeoul = timestamp.toLocalDateTime().atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("zdDt : " + zdDtSeoul);

        Instant instant = zdDtSeoul.toInstant();
        System.out.println("instant : " + instant);

    }

    @Test
    public void strDateToDateTimeUTC() {
        String strDate = "2023-05-17 14:51:05";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt = formatter.parseDateTime(strDate).toDateTime(DateTimeZone.UTC);
        System.out.println("DT : " + dt);

    }

    @Test
    public void UtcToTimestamp() {
        DateTime utcDt = DateTime.now(DateTimeZone.UTC);
        System.out.println("UTC DT : " + utcDt);
        System.out.println("UTC DT Milli : " + utcDt.getMillis());

        System.out.println("Timestamp1 : " + new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis()));
        System.out.println("Timestamp2 : " + new Timestamp(DateTime.now().getMillis()));

        DateTime dt = DateTime.now();
        System.out.println("DT : " + dt);
        System.out.println("DT Milli : " + dt.getMillis());
        System.out.println("Timestamp DT Milli : " + new Timestamp(dt.getMillis()));

        DateTimeZone zoneDefault = DateTimeZone.getDefault();
        System.out.println("zoneDefault : " + zoneDefault);
    }
}
