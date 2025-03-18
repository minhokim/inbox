package etc;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EtcTest {
    private static final Long MEMBER_NO = 9000000000l;

    @Test
    public void randomStr() {
        int min = 111111;
        int max = 999999;

        int randomStr = (int) ((Math.random() * (max - min)) + min);
        System.out.println(Integer.valueOf(randomStr));
    }
    @Test
    public void roundTest() {
        double watt = 3.14159265;
        System.out.println(Math.round(watt * 10) / 10.0);
        System.out.println(Math.round(watt * 100) / 100.0);
        System.out.println(Math.round(watt * 1000) / 1000.0);
    }

    // TODO: 3/8/24 i'm main
    @Test
    public void dayOfWeek() {
        DateTime dateTime = new DateTime(System.currentTimeMillis());

        System.out.println(System.currentTimeMillis());
        System.out.println(dateTime);
        System.out.println(new DateTime(System.currentTimeMillis()).withZone(DateTimeZone.forID("Asia/Seoul")));
        System.out.println(new DateTime(System.currentTimeMillis()));
        System.out.println(dateTime.dayOfWeek().getAsString());
    }

    // TODO: 3/8/24 Git Test
    @Test
    public void mapTest() {
        Map<String, Object> map = null;
        if (map.isEmpty()) {
            System.out.println("empty");
        }
    }

    @Test
    public void emptyTest() {
        String str = null;
        if (StringUtils.isEmpty(str)) {
            System.out.println("empty");
        }
    }

    @Test
    public void doubleTest() {
        int startMeter = 21250;
        int stopMeter = 21270;
        double energyKw = (stopMeter - startMeter) / 1000d;
        int chargeAmount = (int) (energyKw * 220);
        System.out.println(energyKw);
    }

    @Test
    public void rateTest() {
        int price = 1000;
        double disRate = 5.1;

        int chargeAmount = (int) (price - (price * (disRate / 100)));
        System.out.println(chargeAmount);

        double pRate = 3.2;
        int pnt = (int) (chargeAmount * (pRate / 100));
        System.out.println("pnt : " + pnt);

    }

    @Test
    public void capacity() {
        double rangeMin = 0.95;
        double energy = 49.2345;
        double configuredEnergy = 50.0;
        double range = energy / configuredEnergy;

        if (range >= rangeMin) {
            System.out.println("range is " + range + ",  rangeMin is " + rangeMin);
        }

        System.out.println("range : " + range);
    }

    @Test
    public void bigDecimalTest() {
        BigDecimal aa = new BigDecimal("30");
        BigDecimal bb = BigDecimal.valueOf(1000).multiply(aa);

        System.out.println(bb);
    }

    @Test
    public void equalsLong() {
        Long memberNo = 9000000000l;
        if (MEMBER_NO.equals(memberNo)) {
            System.out.println("true");
        }

    }

    @Test
    public void commission() {
        int amt = 244;
        int commission = (int) (amt * (10d / 100));
        System.out.println(commission);

        int vat = (int) (commission * 0.1);
        System.out.println(vat);
    }

    @Test
    public void arrayCopyTest() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Integer[] arr1 = Arrays.copyOfRange(arr, 0, 2);
        Integer[] arr2 = Arrays.copyOfRange(arr, 3, 4);

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        int[] intArr = Arrays.stream(arr)
                .mapToInt(Integer::valueOf)
                .toArray();

        String strJoin = Arrays.stream(intArr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(strJoin);


        Arrays.stream(arr)
                .map(k -> k.toString())

    }



}
