package datetime;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        Instant now = Instant.now();
        org.joda.time.Instant now2 = org.joda.time.Instant.now();
        System.out.println("Now : " + now);
        System.out.println("Joda Now : " + now2);
    }
}
