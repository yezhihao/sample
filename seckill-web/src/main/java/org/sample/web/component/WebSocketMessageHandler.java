package org.sample.web.component;

import org.sample.seckill.model.User;
import org.sample.web.config.SessionKey;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WebSocketMessageHandler extends TextWebSocketHandler {

    private static final AtomicInteger onlineCount = new AtomicInteger();

    private static final Map<Integer, WebSocketSession> webSocketMap = new ConcurrentHashMap();

    public void closeSession(Integer id) {
        WebSocketSession socketSession = webSocketMap.get(id);
        if (socketSession != null) {
            try {
                socketSession.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message) {
        for (WebSocketSession socketSession : webSocketMap.values()) {
            send(socketSession, message);
        }
    }

    public void send(WebSocketSession socketSession, String message) {
        try {
            socketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = (User) session.getAttributes().get(SessionKey.USER);
        webSocketMap.put(user.getId(), session);
        int i = onlineCount.incrementAndGet();
        broadcast(user.getUsername() + " join! Currently online " + i + " people");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (message.getPayloadLength() < 1)
            return;
        User user = (User) session.getAttributes().get(SessionKey.USER);
        message = new TextMessage(user.getUsername() + ": " + message.getPayload());
        for (WebSocketSession socketSession : webSocketMap.values()) {
            socketSession.sendMessage(message);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable error) throws Exception {
        error.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        User user = (User) session.getAttributes().get(SessionKey.USER);
        webSocketMap.remove(user.getId());
        int i = onlineCount.decrementAndGet();
        broadcast(user.getUsername() + " exit.Currently online " + i + " people");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}