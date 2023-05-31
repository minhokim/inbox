package hash;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashTest {
    @Test
    public void sha256Hash() {
        String passwordToHash = "test1234";
        byte[] salt = getSalt();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] encodedHash = digest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            System.out.println("encodedHash : " + encodedHash);
            System.out.println("hex : " + bytesToHex(encodedHash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getSalt() {
        /*SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);*/
        String str = "STA_CH_001";
        byte salt[] = str.getBytes();
        return salt;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
