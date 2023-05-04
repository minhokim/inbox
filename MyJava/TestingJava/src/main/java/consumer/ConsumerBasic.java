package consumer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerBasic {
    private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static int DECIMALS = 2;

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("A", new BigDecimal("2.54")));
        products.add(new Product("B", new BigDecimal("3.89")));
        products.add(new Product("C", new BigDecimal("5.99")));
        products.add(new Product("D", new BigDecimal("9.99")));

        Consumer<Product> inPrice = p -> {
            p.setPrice(rounded(p.getPrice().multiply(new BigDecimal("1.1"))));
        };

        process(products, inPrice.andThen(System.out::println));

        // Refering Method
        Consumer<String> consumer1 = ConsumerBasic::printMessage;
//        Consumer<String> consumer1 = (String x) -> printMessage(x);
        consumer1.accept("Ron");

    }

    private static void printMessage(String name) {
        System.out.println("Hello " + name);
    }

    private static BigDecimal rounded(BigDecimal number){
        return number.setScale(DECIMALS, ROUNDING_MODE);
    }

    private static void process(List<Product> data, Consumer<Product> cons) {
        for (var e : data) {
            cons.accept(e);
        }
    }
}
