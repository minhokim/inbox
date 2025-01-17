package obj;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void testOptional() {
        String str = "aa";
        Optional<String> strOpt = Optional.ofNullable(str);

        System.out.println(strOpt.isPresent());
    }

}
