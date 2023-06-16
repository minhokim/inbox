package net.bgp.ws;

import lombok.RequiredArgsConstructor;
import net.bgp.service.LogService;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.nio.ByteBuffer;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
public class PingTask implements Runnable {
    private final String clientId;
    private final WebSocketSession session;
    private final LogService logService;
    private static final PingMessage PING_MESSAGE = new PingMessage(ByteBuffer.wrap("ping".getBytes(UTF_8)));
    @Override
    public void run() {
        logService.sendingPing(clientId, session);
        try {
            session.sendMessage(PING_MESSAGE);
        } catch (IOException e) {
            logService.pingError(clientId, session, e);
            throw new RuntimeException(e);
        }
    }
}
