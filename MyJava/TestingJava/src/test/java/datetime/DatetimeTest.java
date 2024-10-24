package datetime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
        /*String strDate = "2023-05-17 14:51:05";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt = formatter.parseDateTime(strDate).toDateTime(DateTimeZone.UTC);
        System.out.println("DT : " + dt);*/
    }

    @Test
    public void timestampToStr() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Timestamp : " + timestamp);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        String timestampAsString = formatter.format(timestamp.toLocalDateTime());
        System.out.println(timestampAsString);
        System.out.println(timestamp);
    }

    @Test
    public void convertTimeStampToStringTest() {
        String currentTimeStampStr = convertTimeStampToString(new Timestamp(System.currentTimeMillis()), "yyyyMMdd");
        System.out.println(currentTimeStampStr);
    }

    public static String convertTimeStampToString(Timestamp timestamp, String format)
    {
        if (timestamp == null) return "";

        try
        {
            Date date = new Date();
            date.setTime(timestamp.getTime());
            return new SimpleDateFormat(format).format(date);
        }
        catch (Exception e)
        {
            return "";
        }
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

    @Test
    public void diffTime() {
        String strDateStart = "2024-08-05 10:30:05";
        String strDateEnd = "2024-08-07 12:37:35";
        Timestamp start = Timestamp.valueOf(strDateStart);
        Timestamp end = Timestamp.valueOf(strDateEnd);

        System.out.println("end : " + end);
        System.out.println("start : " + start);

        long diff = end.getTime() - start.getTime();
        System.out.println( (diff / 1000 / 60) / 60 + "h " + (diff / 1000 /60) % 60 + "m" );

        System.out.println(end.compareTo(start));

        long diffMinutes = (diff / 1000) / 60;
        long diffHours = ((diff / 1000) / 60) / 60;
        long diffDays = (((diff / 1000) / 60) / 60) / 24;


        System.out.println("diffHours : " + diffHours);
        System.out.println("diffDays : " + diffDays);

        if (diffMinutes < 60) {
            System.out.println(diffMinutes + "分前に使用");
        }

        if (diffHours < 24) {
            System.out.println(diffHours + "時間" + diffMinutes % 60 + "分前に使用");
        }

        System.out.println(diffDays + "日前に使用");

    }

    @Test
    public void dateTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("No1 : "+ sdf.format(new Date()));
        System.out.println("No2 : "+ new Timestamp(System.currentTimeMillis()).getTime());
        System.out.println("No3 : "+ sdf.format(new Timestamp(System.currentTimeMillis()).getTime()));
    }

    @Test
    public void compareDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = "2024-07-10 23:59:59";
        Timestamp endTimestamp = Timestamp.valueOf(endDate);
        String endTime = sdf.format(endTimestamp);
        System.out.println("End Timestamp : " + endTimestamp);
        System.out.println("End Timestamp yyyy-MM-dd : " + endTime);

        Timestamp todayTimestamp = new Timestamp(System.currentTimeMillis());
        String todayTime = sdf.format(todayTimestamp);
        System.out.println("Today Timestamp : " + todayTimestamp);
        System.out.println("Today Timestamp yyyy-MM-dd: " + todayTime);

        String todayStr = sdf.format(new Date());
        System.out.println("Today Str : " + todayStr);

        System.out.println(endTime.equals(todayTime));

        Timestamp prevEnd = Timestamp.valueOf(endTime + " 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(prevEnd);
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Timestamp newStartTimestamp = new Timestamp(cal.getTime().getTime());
        System.out.println("newStartTimestamp : " + newStartTimestamp);

    }

    @Test
    public void lastDayOfTheMonth() {
        Timestamp todayTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp newNextEndTimestamp = new Timestamp(cal.getTime().getTime());
        System.out.println("newNextEndTimestamp : " + newNextEndTimestamp);
    }

    @Test
    public void firstDayOfThePrevMonth() {
        Timestamp todayTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        Timestamp prevMonthDate = new Timestamp(cal.getTime().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDay = sdf.format(prevMonthDate);
        System.out.println("firstDay : " + firstDay);
    }

    @Test
    public void lastDayOfThePrevMonth() {
        Timestamp todayTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        Timestamp prevMonthDate = new Timestamp(cal.getTime().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDay = sdf.format(prevMonthDate);
        System.out.println("lastDay : " + lastDay);
    }

    @Test
    public void laterOneMonth() {
        Timestamp todayTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.add(Calendar.MONTH, 1);

        Timestamp nextMonthDate = new Timestamp(cal.getTime().getTime());
        System.out.println(nextMonthDate);
    }

    @Test
    public void prevMonth() {
        Timestamp todayTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.add(Calendar.MONTH, -1);

        Timestamp prevMonthDate = new Timestamp(cal.getTime().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String prevMonth = sdf.format(prevMonthDate);

        System.out.println("prevMonth : " + prevMonth);
    }

    @Test
    public void nextEndDay() {
        String start = "2024-05-31 13:59:59";
        Timestamp todayTimestamp = Timestamp.valueOf(start);
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.add(Calendar.MONTH, 1);
        if (true) {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        }

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp newNextEndTimestamp = new Timestamp(cal.getTime().getTime());
        System.out.println("newNextEndTimestamp : " + newNextEndTimestamp);
    }

    @Test
    public void plusOneDay() {
        String start = "2024-08-05 16:40:05";
        Timestamp todayTimestamp = Timestamp.valueOf(start);
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayTimestamp);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp newNextEndTimestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(newNextEndTimestamp);
    }


    @Test
    public void compareTimestamp() {
        String start = "2024-10-01 00:00:00";
        String end = "2024-12-31 00:00:00";

        Timestamp startTimestamp = Timestamp.valueOf(start);
        Timestamp endTimestamp = Timestamp.valueOf(end);

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        System.out.println("currentTimestamp : " + currentTimestamp);
        System.out.println("startTimestamp : " + startTimestamp);
        System.out.println("endTimestamp : " + endTimestamp);

        System.out.println(currentTimestamp.compareTo(startTimestamp));
        System.out.println(currentTimestamp.compareTo(endTimestamp));
        System.out.println(currentTimestamp.compareTo(currentTimestamp));

        System.out.println(currentTimestamp.after(startTimestamp));
        System.out.println(currentTimestamp.before(endTimestamp));

        System.out.println(currentTimestamp.after(currentTimestamp));
        System.out.println(currentTimestamp.before(currentTimestamp));
    }

    @Test
    public void setMidnight() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("currentTimestamp1 : " + currentTimestamp);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTimestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp newCurrentTimestamp = new Timestamp(calendar.getTime().getTime());

        System.out.println("newCurrentTimestamp : " + newCurrentTimestamp);
    }

    @Test
    public void timestampValueOf() {
        String date = "2024-08-30";
        Timestamp timestamp = Timestamp.valueOf(date + " 00:00:00");

        System.out.println("timestamp : " + timestamp);
    }

    @Test
    public void ttt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now().minusDays(1);
        System.out.println(formatter.format(date));
    }


}
