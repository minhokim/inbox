package encoding;

import org.junit.jupiter.api.Test;

import java.util.Base64;

public class Base64Test {

    @Test
    public void encodeDecode() {
        //Encode
        String orgInput = "1002-1111-2222-3333";
        String encodingStr = Base64.getEncoder().encodeToString(orgInput.getBytes());
        System.out.println("encodingStr : " + encodingStr);

        //Decode
        String encodingStr2 = "RVZfTW9iaWxlOmlsb3ZlaW9uZXY=";
        byte[] decodedBytes = Base64.getDecoder().decode(encodingStr2);
        String decodedStr = new String(decodedBytes);
        System.out.println("decodedStr : " + decodedStr);
    }
}
