package etc;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class ByteTest {

    @Test
    public void getByte() {
        String cardNo = "1111-2222-3333-4444";
        System.out.println(cardNo.getBytes().length);
    }
}
