package list;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ListTest {

    @Test
    public void streamFindFirst() {
        List<Pay> list = getList();
        Optional<Pay> pay = list.stream().filter(f -> f.getName().equals("mon")).findAny();
        System.out.println("pay : " + pay.get().getName());
    }

    @Test
    public void subList() {
        List<Pay> list = getList();
        List<Pay> subList = list.subList(0, 2);
        System.out.println(subList.size());
        List<Pay> subList2 = list.subList(3, list.size());
        System.out.println(subList2.size());
    }

    @Test
    public void mapToInt() {
        List<Pay> list = getList();
        int sumMoney = list.stream().mapToInt(Pay::getMoney).sum();
        assertThat(sumMoney).isEqualTo(200);
    }

    @Test
    public void map() {
        List<Pay> list = getList();
        int sumMoney = list.stream().map(row -> row.getMoney()).reduce(0, (a, b) -> a + b);
        assertThat(sumMoney).isEqualTo(4);
    }

    @Test
    public void filter() {
        List<Pay> list = getList();
        List<Pay> tueList = list.stream().filter(row -> "tue".equals(row.getName())).collect(Collectors.toList());
        assertThat(tueList.size()).isEqualTo(1);
    }

    @Test
    public void toMap() {
        List<Pay> list = getList();
        Map<String, Integer> map = list.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney()) );
        System.out.println(map.values());
    }

    /**
     * 중복된 Key가 존재하는 경우 3번째 인자인 mergeFunction을 사용하여 처리
     * 아래의 경우 두 값을 Sum
     */
    @Test
    public void toMapMerge() {
        List<Pay> list = getList();
        Map<String, Integer> map = list.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney(), Integer::sum) );
        assertThat(map.get("wed")).isEqualTo(2);
        System.out.println(map.values());
    }

    @Test
    public void toMapMerge2() {
        List<Pay> list = getList();
        Map<String, Integer> map = list.stream().collect( Collectors.toMap(Pay::getName, Pay::getMoney, Integer::sum) );
        System.out.println(map.values());
    }

    /**
     * 아래의 경우 새로운 값으로 대체
     */
    @Test
    public void toMapMergeReplace() {
        List<Pay> list = getList();
        Map<String, Integer> map = list.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney(), (oldValue, newValue) -> newValue) );
        assertThat(map.get("wed")).isEqualTo(20);
    }

    private static List<Pay> getList() {
        List<Pay> pays = new ArrayList<>();
        Pay onePay = new Pay();
        onePay.setName("mon");
        onePay.setMoney(1);

        Pay twoPay = new Pay();
        twoPay.setName("tue");
        twoPay.setMoney(1);

        Pay threePay = new Pay();
        threePay.setName("wed");
        threePay.setMoney(1);

        Pay fourPay = new Pay();
        fourPay.setName("wed");
        fourPay.setMoney(1);

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
