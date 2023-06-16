package net.bgp.ws;

import net.bgp.config.websocket.WebSocketConfig;
import net.bgp.enums.BgpVersion;
import net.bgp.service.LogService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class WebSocketEndpoint extends ConcurrentWebSocketHandler implements SubProtocolCapable {

    public static final String CLIENT_ID_KEY = "CLIENT_ID_KEY";
    private LogService logService;

    public WebSocketEndpoint(LogService logService) {
        this.logService = logService;
    }

    @Override
    void onMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage) {
            System.out.println("onMessage : " + message);
        } else if (message instanceof BinaryMessage) {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Binary messages not supported"));
        } else {
            throw new IllegalStateException("Unexpected WebSocket message type: " + message);
        }
    }

    @Override
    void onOpen(WebSocketSession session) throws Exception {
        String clientId = getClientId(session);
        logService.connected(clientId, session);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        ScheduledFuture pingSchedule = service.scheduleAtFixedRate(
                new PingTask(clientId, session, logService),
                WebSocketConfig.PING_INTERVAL,
                WebSocketConfig.PING_INTERVAL,
                TimeUnit.MINUTES
        );
    }

    private String getClientId(WebSocketSession session) {
        return (String) session.getAttributes().get(CLIENT_ID_KEY);
    }

    @Override
    void onClose(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String clientId = getClientId(session);
        logService.closed(clientId, session, closeStatus);
    }

    @Override
    void onError(WebSocketSession session, Throwable throwable) throws Exception {

    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList(getVersion().getValue());
    }

    public BgpVersion getVersion() {
        return BgpVersion.V_11;
    }
}
