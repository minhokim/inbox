package list;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ListTest {

    @Test
    public void asListTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void removeTest2() {
        List<Pay> list = getList();
        Iterator<Pay> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (isRemove(iterator.next())) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    /**
     * ConcurrentModificationException
     */
    @Test
    public void removeTest() {
        List<Pay> list = getList();
        for (Pay pay : list) {
            if (isRemove(pay)) {
                list.remove(pay);
            }
        }
    }

    private boolean isRemove(Pay pay) {
        if ("mon".equals(pay.getName())) {
            return true;
        }
        return false;
    }

    @Test
    public void streamFindFirst() {
        List<Pay> list = getList();
        Optional<Pay> pay = list.stream().filter(f -> f.getName().equals("mon")).findAny();
        Optional<Pay> pay2 = list.stream().filter(f -> f.getName().equals("mon")).findFirst();
        System.out.println("pay : " + pay.get().getName());
        System.out.println("pay2 : " + pay2.get().getName());
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
        Pay fourPay = new Pay("wed", 2);
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
        Pay fourPay = new Pay("web", 2);
        list.add(fourPay);

        Map<String, Integer> map = list.stream().collect( Collectors.toMap(row -> row.getName(), row -> row.getMoney(), (oldValue, newValue) -> newValue) );
        assertThat(map.get("wed")).isEqualTo(2);
    }


    @Test
    public void sumWithObject() {
        List<Pay> items = getDayList();

        Integer sum = items.stream()
                .map(x -> x.getMoney())
                .collect(Collectors.summingInt(Integer::intValue));

        System.out.println("sum : " + sum);
    }

    @Test
    public void reduce() {
        List<Pay> items = getDayList();

        Integer sum = items.stream()
                .map(item -> item.getMoney())
                .reduce(0, Integer::sum);

        System.out.println("reduce sum : " + sum);
    }

    @Test
    public void groupingByTest() {
        List<Pay> items = new ArrayList<>(getDayList());
        items.sort(Comparator.comparing(Pay::getName));
        TreeMap<String, List<Pay>> collect = items.stream()
                .collect(groupingBy(Pay::getName, TreeMap::new, Collectors.toList()));

        System.out.println(collect);
    }

    @Test
    public void groupingCountTest() {
        List<Pay> items = new ArrayList<>(getDayList());
        Map<String, Long> collect = items.stream()
                .collect(groupingBy(Pay::getName, counting()));

        System.out.println(collect);
    }

    private static List<Pay> getList() {
        return List.of(
                new Pay("web", 1),
                new Pay("wed", 1),
                new Pay("mon", 1),
                new Pay("tue", 1)
        );
    }

    private static List<Pay> getDayList() {
        return List.of(
                new Pay("7", 1),
                new Pay("5", 1),
                new Pay("3", 1),
                new Pay("1", 1),
                new Pay("7", 1),
                new Pay("5", 1),
                new Pay("3", 1)
        );
    }

    // TODO: 3/7/24  Test
    @Setter
    @Getter
    public static class Pay {
        private String name;
        private int money;

        public Pay(String name, int money) {
            this.name = name;
            this.money = money;
        }
    }
}
