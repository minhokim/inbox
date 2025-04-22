package kr.re.bgp.jpademo.service;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaExample {
    public static void main(String... argv) {

        try {
            File publicKeyFile = new File("./src/main/resources/bgpdemo.pub");
            String publicKeyString = Files.readString(publicKeyFile.toPath());
            System.out.println("publicKeyString : " + publicKeyString);

            byte[] decodedPublicKey = Base64.getDecoder().decode( publicKeyString ) ;

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);
            PublicKey _publicKey = keyFactory.generatePublic(publicKeySpec);

            File privateKeyFile = new File("./src/main/resources/bgpdemo.key");
            String privateKeyString = Files.readString(privateKeyFile.toPath());
            System.out.println("privateKeyString : " + privateKeyString);

            byte[] decodedPrivateKey = Base64.getDecoder().decode( privateKeyString );

            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
            PrivateKey _privateKey = keyFactory.generatePrivate(privateKeySpec);

            //The private key can be used to sign (not encrypt!) a message. The public key holder can then verify the message.

            String message = "To Be or not To Be";

            //Let's sign our message
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(_privateKey);
            privateSignature.update(message.getBytes(StandardCharsets.UTF_8));

            byte[] signature = privateSignature.sign();

            //Let's check the signature
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(_publicKey);
            publicSignature.update(message.getBytes(StandardCharsets.UTF_8));
            boolean isCorrect = publicSignature.verify(signature);

            System.out.println("Signature correct: " + isCorrect);

            //The public key can be used to encrypt a message, the private key can be used to decrypt it.
            //Encrypt the message
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, _publicKey);

            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedMessageBytes = encryptCipher.doFinal(messageBytes);

            //Now decrypt it
            Cipher decriptCipher = Cipher.getInstance("RSA");
            decriptCipher.init(Cipher.DECRYPT_MODE, _privateKey);

            byte[] decryptedMessageBytes = decriptCipher.doFinal(encryptedMessageBytes);
            String decipheredMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

            System.out.println(decipheredMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
