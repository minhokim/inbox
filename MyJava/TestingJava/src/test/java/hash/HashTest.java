package hash;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTest {
    @Test
    public void sha256Hash() {
        String passwordToHash = "test1234";
        byte[] encodedHash = getHashValue(passwordToHash);
        System.out.println("hex : " + bytesToHex(encodedHash));
    }

    @Test
    public void getHex() {
        System.out.println(String.format("%02x", 10000));
    }

    private static byte[] getHashValue(String value) {
        try {
            byte[] salt = getSalt();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] encodedHash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return encodedHash;
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
