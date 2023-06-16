package tls;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.Test;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class MutualTlsTest {

    @Test
    public void webSocketMutualTls() {
        final String TRUST_STORE = "/jks/client_truststore.jks";
        final String TRUST_STORE_PASSWORD = "qwer1234";
        final String TRUST_STORE_TYPE = "JKS";
        final String KEY_STORE = "/jks/client_keystore.jks";
        final String KEY_STORE_PASSOWRD = "qwer1234";
        final String KEY_STORE_TYPE = "JKS";
        final String SERVER_ENDPOINT = "wss://domain.net:6080/rest/ocpp/STA_CH_001";

        KeyStore keyStore = null;
        KeyStore trustStore = null;
        try {
            InputStream keyStoreIs = this.getClass().getResourceAsStream(KEY_STORE);
            keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
            keyStore.load(keyStoreIs, KEY_STORE_PASSOWRD.toCharArray());

            InputStream trustStoreIs = this.getClass().getResourceAsStream(TRUST_STORE);
            trustStore = KeyStore.getInstance(TRUST_STORE_TYPE);
            trustStore.load(trustStoreIs, TRUST_STORE_PASSWORD.toCharArray());

            final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, KEY_STORE_PASSOWRD.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);

            final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            Map<String, String> httpHeaders = new HashMap<>();
            httpHeaders.put("Sec-WebSocket-Protocol", "ocpp1.6");

            MyClient session = new MyClient(URI.create(SERVER_ENDPOINT), httpHeaders);
            session.setSocketFactory(sslSocketFactory);
            session.connectBlocking();

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException |
                 UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void httpClientMutualTls() throws KeyManagementException {
        final String TRUST_STORE = "/jks/client_truststore.jks";
        final String TRUST_STORE_PASSWORD = "qwer1234";
        final String TRUST_STORE_TYPE = "JKS";
        final String KEY_STORE = "/jks/client_keystore.jks";
        final String KEY_STORE_PASSOWRD = "qwer1234";
        final String KEY_STORE_TYPE = "JKS";
        final String SERVER_ENDPOINT = "https://domain.net:6080/rest/tls";

        KeyStore keyStore = null;
        KeyStore trustStore = null;
        try {
            InputStream keyStoreIs = this.getClass().getResourceAsStream(KEY_STORE);
            keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
            keyStore.load(keyStoreIs, KEY_STORE_PASSOWRD.toCharArray());

            InputStream trustStoreIs = this.getClass().getResourceAsStream(TRUST_STORE);
            trustStore = KeyStore.getInstance(TRUST_STORE_TYPE);
            trustStore.load(trustStoreIs, TRUST_STORE_PASSWORD.toCharArray());

            final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, KEY_STORE_PASSOWRD.toCharArray());

            final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);

            final SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            SSLParameters sslParameters = new SSLParameters();
            sslParameters.setNeedClientAuth(true);

            HttpClient httpClient = HttpClient.newBuilder()
                            .sslContext(sslContext)
                            .sslParameters(sslParameters)
                            .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER_ENDPOINT))
                    .header("Authorization", "I-ON-EV")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Body : " + response.body());

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException |
                 UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class MyClient extends WebSocketClient {


    public MyClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("onOpen");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("onMessage");
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("onClose");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("onError");
    }
}
