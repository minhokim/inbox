package consumer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerCallbackTest {

    public static void main(String[] args) {
        List<Consumer<String>> callbacks = new ArrayList<>();
        callbacks.add((id) -> System.out.println("id : " + id));
        callbacks.add((id) -> System.out.println("id : " + id));

        callbacks.forEach(consumer -> consumer.accept("1"));
    }

}
