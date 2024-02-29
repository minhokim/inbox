package optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void orElse() {
        String str = "orElse";
        String nullStr = null;
        String res = Optional.ofNullable(nullStr).orElse(defaultStr()); //str이 null or not null 둘 다 메소드 호출됨
        System.out.println(res);
    }

    @Test
    public void orElseGet() {
        String str = "orElseGet";
        String nullStr = null;
        String res = Optional.ofNullable(nullStr).orElseGet(() -> defaultStr()); //str이 null일 때 호출됨
        System.out.println(res);
    }

    public static String defaultStr() {
        System.out.println("Call defaultStr");
        return "default";
    }


}
