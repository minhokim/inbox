package net.bgp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Service
public class LogService {

    public void connected(String clientId, WebSocketSession session) {
        log.info("[clientId={}, sessionId={}] Connection is established", clientId, session.getId());
    }

    public void closed(String clientId, WebSocketSession session, CloseStatus closeStatus) {
        log.warn("[clientId={}, sessionId={}] Connection is closed, status: {}", clientId, session.getId(), closeStatus);
    }

    public void receivedText(String clientId, WebSocketSession session, String msg) {
        log.info("[clientId={}, sessionId={}] Received: {}", clientId, session.getId(), msg);
    }

    public void sending(String clientId, WebSocketSession session, String msg) {
        log.info("[clientId={}, sessionId={}] Sending: {}", clientId, session.getId(), msg);
    }

    public void sendingPing(String clientId, WebSocketSession session) {
        log.debug("[clientId={}, sessionId={}] Sending ping message", clientId, session.getId());
    }

    public void pingError(String clientId, WebSocketSession session, Throwable t) {
        if (log.isErrorEnabled()) {
            log.error("[clientId=" + clientId + ", sessionId=" + session.getId() + "] Ping error", t);
        }
    }
}
