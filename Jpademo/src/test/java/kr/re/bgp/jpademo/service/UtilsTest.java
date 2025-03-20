package kr.re.bgp.jpademo.service;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

public class UtilsTest {

    @Test
    public void dateTest() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        System.out.println(date.getTime());
        System.out.println(timestamp);

        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        System.out.println(dateTime);
    }
}
