package listtest;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ListTest {
    @Test
    public void streamTest() {
        List<Pay> pays = getPays();

        int mapToIntSumMoney = mapToInt(pays);
        assertThat(mapToIntSumMoney).isEqualTo(210);

        int mapSumMoney = map(pays);
        assertThat(mapSumMoney).isEqualTo(210);

        List<Pay> tuePays = filter(pays);
        assertThat(tuePays.size()).isEqualTo(1);

        assertThat(pays.size()).isEqualTo(4);

//        toMap(pays);

        Map<String, Integer> mergeMap = toMapMerge(pays);
        assertThat(mergeMap.get("wed")).isEqualTo(60);
    }

    public static int mapToInt(List<Pay> pays) {
        int sumMoney = pays.stream().mapToInt(Pay::getMoney).sum();
        return sumMoney;
    }

    public static int map(List<Pay> pays) {
        int sumMoney = pays.stream().map(row -> row.getMoney()).reduce(0, (a, b) -> a + b);
        return sumMoney;
    }

    public static List<Pay> filter(List<Pay> pays) {
        return pays.stream().filter(row -> "tue".equals(row.getName())).collect(Collectors.toList());
    }

    public static Map<String, Integer> toMap(List<Pay> pays) {
        Map<String, Integer> map = pays.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney()) );
        return map;
    }

    /**
     * 중복된 Key가 존재하는 경우 3번째 인자인 mergeFunction을 사용하여 처리할 수 있음
     * @param pays
     * @return
     */
    public static Map<String, Integer> toMapMerge(List<Pay> pays) {
        Map<String, Integer> map = pays.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney(), Integer::sum) );
        return map;
    }

    public static Map<String, Integer> toMap2(List<Pay> pays) {
        Map<String, Integer> map = pays.stream().collect( Collectors.toMap(Pay::getName, Pay::getMoney, Integer::sum) );
        return map;
    }

    private static List<Pay> getPays() {
        List<Pay> pays = new ArrayList<>();
        Pay onePay = new Pay();
        onePay.setName("mon");
        onePay.setMoney(100);

        Pay twoPay = new Pay();
        twoPay.setName("tue");
        twoPay.setMoney(50);

        Pay threePay = new Pay();
        threePay.setName("wed");
        threePay.setMoney(30);

        Pay fourPay = new Pay();
        fourPay.setName("wed");
        fourPay.setMoney(30);

        pays.add(onePay);
        pays.add(twoPay);
        pays.add(threePay);
        pays.add(fourPay);

        return pays;
    }


    @Setter
    @Getter
    public static class Pay {
        private String name;
        private int money;
    }
}
