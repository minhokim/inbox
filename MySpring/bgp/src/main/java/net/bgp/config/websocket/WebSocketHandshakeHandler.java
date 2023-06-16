package net.bgp.config.websocket;

import net.bgp.utils.Functions;
import net.bgp.ws.WebSocketEndpoint;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.Map;

@Component
public class WebSocketHandshakeHandler implements HandshakeHandler {

    private WebSocketEndpoint endpoint;

    public WebSocketHandler getDummyWebSocketHandler() {
        return new TextWebSocketHandler();
    }

    public WebSocketHandshakeHandler(WebSocketEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public boolean doHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws HandshakeFailureException {
        String clientId = Functions.getLastBitFromUrl(request.getURI().getPath());
        attributes.put(WebSocketEndpoint.CLIENT_ID_KEY, clientId);
        return new DefaultHandshakeHandler().doHandshake(request, response, endpoint, attributes);
    }
}
