package list;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ListTest {

    @Test
    public void streamFindFirst() {
        List<Pay> list = getList();
        Optional<Pay> pay = list.stream().filter(f -> f.getName().equals("mon")).findAny();
        Optional<Pay> pay2 = list.stream().filter(f -> f.getName().equals("mon")).findFirst();
        System.out.println("pay : " + pay.get().getName());
        System.out.println("pay2 : " + pay.get().getName());
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

    @Test
    public void sortList() {
        List<Pay> list = getList();

        list.sort(Comparator.comparing(Pay::getName).reversed());
        list.forEach(l -> System.out.println(l.getName() + " : " + l.getMoney()));
    }

    /**
     * List to Map
     * 중복된 Key가 존재하는 경우 3번째 인자인 mergeFunction을 사용하여 처리
     * 아래의 경우 두 값을 Sum
     */
    @Test
    public void listToMapBySum() {
        List<Pay> list = getList();
        Pay fourPay = new Pay();
        fourPay.setName("wed");
        fourPay.setMoney(2);
        list.add(fourPay);

        Map<String, Integer> firstMap = list.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney(), Integer::sum) );
        assertThat(firstMap.get("wed")).isEqualTo(3);
        System.out.println(firstMap.values());

        Map<String, Integer> secondMap = list.stream().collect( Collectors.toMap(Pay::getName, Pay::getMoney, Integer::sum) );
        assertThat(secondMap.get("wed")).isEqualTo(3);

        System.out.println(secondMap.values());
    }


    /**
     * List to Map
     * 아래의 경우 새로운 값으로 대체
     */
    @Test
    public void listToMapReplace() {
        List<Pay> list = getList();
        Pay fourPay = new Pay();
        fourPay.setName("wed");
        fourPay.setMoney(2);
        list.add(fourPay);

        Map<String, Integer> map = list.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney(), (oldValue, newValue) -> newValue) );
        assertThat(map.get("wed")).isEqualTo(2);
    }

    private static List<Pay> getList() {
        List<Pay> pays = new ArrayList<>();
        Pay threePay = new Pay();
        threePay.setName("wed");
        threePay.setMoney(1);

        Pay onePay = new Pay();
        onePay.setName("mon");
        onePay.setMoney(1);

        Pay twoPay = new Pay();
        twoPay.setName("tue");
        twoPay.setMoney(1);

        pays.add(onePay);
        pays.add(twoPay);
        pays.add(threePay);

        return pays;
    }


    @Setter
    @Getter
    public static class Pay {
        private String name;
        private int money;
    }
}
