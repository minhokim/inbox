package encrypt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;


public class AESTest {
    public static String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String KEY = "abcdefghijklmnop";
    private static final String IV = KEY.substring(0, 16); // 16byte

    @Test
    public void encryptDecrypt() {
        try {
            String message = "20";
            System.out.println("secret message: " + message);

            String encMsg = encrypt(message);
            System.out.println("encrypted > " + encMsg);

            String decMsg = decrypt(encMsg);
            System.out.println("decrypted > " + decMsg);

            assertThat(message.equals(decMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
