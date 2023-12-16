package tls;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebSocketClientTest {
    @Test
    public void webSocketTest() {
        URI uri = URI.create("wss://localhost:6090/rest/ocpp/STA_CH_001");
        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("Authorization", "Basic U1RBX0NIXzAwMWETSp0ZXN0MTIzNA==");
        httpHeaders.put("Sec-WebSocket-Protocol", "ocpp1.6");
        WebSocketUtil webSocketUtil = new WebSocketUtil(uri, new Draft_6455(), httpHeaders, 5000);

        try {
            webSocketUtil.connectBlocking();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String message = "[\n" +
                "    2, \"10\",\n" +
                "    \"SignCertificate\",\n" +
                "    {\n" +
                "        \"csr\": \"-----BEGIN CERTIFICATE REQUEST-----\\nMIIClTCCAX0CAQAwUDELMAkGA1UEBhMCS1IxETAPBgNVBAoMCGJncCBJbmMuMRgw\\nFgYDVQQLDA9iZ3AgU1NMIFByb2plY3QxFDASBgNVBAMMC2JncC5kZXYubmV0MIIB\\nIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0TNoVdlyWFnMNtxE5eM//tmb\\naTxj95uZgud3eGvhY7lpzED9gt+zueFvrZ2zdqAq7aiZFBab8/QC4iL4TaLLYZyu\\nNWXnKTEjgreGwYj1psmmdHafqAioid2IFaacBHqrUuGb89MOF8tmm+Y39zm1V43+\\njqIkWRyZpnPKQS+M3UwnsZ6ySCd/ukDs5mjf/HQ8NgCh8hB6Flvfa/FEzsZK+yl6\\nmn3wSTLLX+NSSjg1vSp7oCw2yOVnUff6U6iJVL5FPNZBWhODkR5rpskOmaUyFUtN\\nssb5niFfFEIvkO1z8IyIo7ImUVsa5VcFHPus8c9UgQ2+QVQdSjE+o7Xb72Cw8QID\\nAQABoAAwDQYJKoZIhvcNAQEFBQADggEBANAMcuafAzrWGltwR9ptvQZuK9sEH2rM\\nuc9O54O4Cb5nbpM/g5vpBkB1D5J9swbdAWM6iiO95JHjhJrVgzwIWLZK1gcHEIBJ\\njnmDXrbB7IIZRIbxlPBW/hfsG+to9qKmduzSrXzTCtNh8PuWgkWRXQefZCBUwhJi\\n9rwvQYSh7PGCHXvGBT221bsMyRcmDr0b1IXytyccrshA84MpvrTgzgyNJqUqBwX7\\nJIh71XIpASK+yNZ6i/s/U/UcOOyDPmlPR3z4Fxc5Dv8CkHWjvr1BS1yLuTsdFi/G\\njrhid5Sj2o6fmYp6HnFwxyMWOJxhncWZtrYHOaRfcUwO0y4iWRDRZoE=\\n-----END CERTIFICATE REQUEST-----\"\n" +
                "    }\n" +
                "]";

        webSocketUtil.send(message);
        webSocketUtil.close();
    }
}

class WebSocketUtil extends WebSocketClient {

    private JSONObject obj;


    public WebSocketUtil(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    @Override
    public void onMessage( String message ) {

        obj = new JSONObject(message);

    }

    @Override
    public void onOpen( ServerHandshake handshake ) {
        System.out.println( "opened connection" );
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "closed connection" );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
    }

    public JSONObject getResult() {
        return this.obj;
    }

}