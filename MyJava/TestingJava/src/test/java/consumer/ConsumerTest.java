package consumer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    @Test
    public void basicStep1() {
        System.out.println("----- basicStep1 -----");
        Consumer<String> print = x -> System.out.println(x);
        print.accept("java");
    }

    @Test
    public void basicStep2() {
        System.out.println("----- basicStep2 -----");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Consumer<Integer> consumer =  (Integer x) -> System.out.println(x);
        forEach(list, consumer);
    }

    @Test
    public void basicStep3() {
        System.out.println("----- basicStep3 -----");
        List<String> list = Arrays.asList("a", "ab", "abc");
        forEach(list, (String x) -> System.out.println(x.length()));
    }

    static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}
