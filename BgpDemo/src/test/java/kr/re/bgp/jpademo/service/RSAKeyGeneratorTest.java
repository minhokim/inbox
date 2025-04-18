package kr.re.bgp.jpademo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.*;
import java.util.Base64;

public class RSAKeyGeneratorTest {

    public static void main(String[] args) throws Exception {

        //First generate a public/private key pair
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();


        //base64 encoding
        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("encodedPublicKey: " + encodedPublicKey);

        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("encodedPrivateKey: " + encodedPrivateKey);

        //Storing in files
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/bgpdemo.key"))) {
            writer.write("-----BEGIN PRIVATE KEY-----\n");
            writer.write(encodedPrivateKey);
            writer.write("\n-----END PRIVATE KEY-----\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/bgpdemo.pub"))) {
            writer.write("-----BEGIN PUBLIC KEY-----\n");
            writer.write(encodedPublicKey);
            writer.write("\n-----END PUBLIC KEY-----\n");
        }

    }
}
