package consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerList {
    public static void main(String[] args) {
        List<Consumer<String>> consumers = new ArrayList<>();
        consumers.add((String x) -> System.out.println("aa + " + x));
        consumers.add((String x) -> System.out.println("bb + " + x));

        consumers.forEach(consumer -> consumer.accept("TT"));
    }
}
