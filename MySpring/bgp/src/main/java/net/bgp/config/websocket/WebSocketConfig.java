package net.bgp.config.websocket;

import net.bgp.ws.WebSocketHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.concurrent.TimeUnit;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandshakeHandler webSocketHandshakeHandler;
    public static final String PATH_INFIX = "/chat/";
    public static final long PING_INTERVAL = TimeUnit.MINUTES.toMinutes(1);

    public static final int MAX_MSG_SIZE = 8_388_608;   //8 MB for max message size


    public WebSocketConfig(WebSocketHandshakeHandler webSocketHandshakeHandler) {
        this.webSocketHandshakeHandler = webSocketHandshakeHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandshakeHandler.getDummyWebSocketHandler(), PATH_INFIX + "*")
                .setHandshakeHandler(webSocketHandshakeHandler)
                .setAllowedOrigins("*");
    }
}
