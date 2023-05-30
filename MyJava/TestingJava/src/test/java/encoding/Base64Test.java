package encoding;

import org.junit.jupiter.api.Test;

import java.util.Base64;

public class Base64Test {

    @Test
    public void encode() {
        //Encode
        String orgInput = "Dream";
        String encodingStr = Base64.getEncoder().encodeToString(orgInput.getBytes());
        System.out.println("encodingStr : " + encodingStr);

        //Decode
        byte[] decodedBytes = Base64.getDecoder().decode(encodingStr);
        String decodedStr = new String(decodedBytes);
        System.out.println("decodedStr : " + decodedStr);

    }
}
