package consumer;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerBasicTest {

    @Test
    public void basicStep1() {
        Consumer<String> print = (String x) -> System.out.println(x);
        print.accept("java");
    }

    @Test
    public void basicStep2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Consumer<Integer> consumer = (Integer x) -> System.out.println(x);
        forEach(list, consumer);
    }

    @Test
    public void basicStep3() {
        List<String> list = Arrays.asList("a", "ab", "abc");
        forEach(list, (String x) -> System.out.println(x.length()));
    }

    @Test
    public void consumerEx() {
        Consumer<Integer> multiplyBy100 = (Integer x) -> System.out.println(x * 100);

        multiplyBy100.accept(3);
    }

    @Test
    public void consumerEx2() {
        var words = List.of("falcon", "wood", "rock", "forest", "river", "water");
        words.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void consumerAndThenEx() {
        var vals = new ArrayList<Integer>();
        vals.add(2);
        vals.add(4);
        vals.add(6);
        vals.add(8);

        Consumer<List<Integer>> addTwo = list -> {
            for (int i=0; i<list.size(); i++) {
                list.set(i, 2 + list.get(i));
            }
        };

        Consumer<List<Integer>> showList = list -> list.forEach(System.out::println);
        addTwo.andThen(showList).accept(vals);
    }

    @Test
    public void consumerAndThenEx2() {
        Consumer<Integer> c1 = i -> System.out.println(5 * i);
        Consumer<Integer> c2 = i -> System.out.println(5 + i);
        Consumer<Integer> c3 = i -> System.out.println(i);

        c1.andThen(c2).andThen(c3).accept(4);
        c1.andThen(i -> System.out.println(3 * i)).accept(4);
    }

    @Test
    public void consumerEx3() {
        var data = List.of(1, 2, 3, 4, 5, 6, 7);

        Consumer<Integer> consumer = (Integer x) -> System.out.println(x);
//        Consumer<Integer> consumer = System.out::println;
        forEach(data, consumer);
        System.out.println("----------------");
        forEach(data, System.out::println);
    }

    @Test
    public void consumerEx4() {
        var list = List.of(10, 20, 30, 40, 50);

//        Consumer<List<Integer>> consumer = (List<Integer> x) -> addList(x);
        Consumer<List<Integer>> consumer = ConsumerBasicTest::addList;
        consumer.accept(list);
    }

    static void addList(List<Integer> list) {
        int result = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum : " + result);
    }

    static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}
