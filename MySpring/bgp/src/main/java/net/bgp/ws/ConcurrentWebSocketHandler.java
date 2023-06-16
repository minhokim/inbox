package net.bgp.ws;

import net.bgp.config.websocket.WebSocketConfig;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public abstract class ConcurrentWebSocketHandler implements WebSocketHandler {

    private static final int SEND_TIME_LIMIT = (int) TimeUnit.SECONDS.toMillis(10);
    private static final int BUFFER_SIZE_LIMIT = 5 * WebSocketConfig.MAX_MSG_SIZE;
    private final Map<String, ConcurrentWebSocketSessionDecorator> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.onOpen(internalGet(session));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        this.onMessage(internalGet(session), message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        this.onError(internalGet(session), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        this.onClose(sessions.remove(session.getId()), closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private ConcurrentWebSocketSessionDecorator internalGet(WebSocketSession session) {
        return sessions.computeIfAbsent(session.getId(), s -> new ConcurrentWebSocketSessionDecorator(session, SEND_TIME_LIMIT, BUFFER_SIZE_LIMIT));
    }

    abstract void onMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception;
    abstract void onOpen(WebSocketSession session) throws Exception;
    abstract void onClose(WebSocketSession session, CloseStatus closeStatus) throws Exception;
    abstract void onError(WebSocketSession session, Throwable throwable) throws Exception;
}
