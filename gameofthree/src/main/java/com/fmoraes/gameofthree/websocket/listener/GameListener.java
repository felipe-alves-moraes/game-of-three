package com.fmoraes.gameofthree.websocket.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmoraes.gameofthree.domain.GameService;
import com.fmoraes.gameofthree.domain.entity.*;
import com.fmoraes.gameofthree.websocket.PlayerSession;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.lang.Error;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameListener extends TextWebSocketHandler {

    private final Map<PlayerSession, PlayerSession> gameSession = new ConcurrentHashMap<>();
    private final ObjectMapper mapper;
    private final GameService service;

    public GameListener(ObjectMapper mapper, GameService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        if (gameSession.isEmpty()) {
            gameSession.put(new PlayerSession(session.getId(), session), new PlayerSession(null, null));
        } else {
            Map.Entry<PlayerSession, PlayerSession> availablePlayer = gameSession.entrySet().stream()
                    .filter(entry -> entry.getValue().getId() == null)
                    .findFirst().orElse(null);
            if (availablePlayer != null) {
                PlayerSession player2Session = new PlayerSession(session.getId(), session);
                gameSession.put(availablePlayer.getKey(), player2Session);
                gameSession.put(player2Session, availablePlayer.getKey());
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        PlayerSession playerSession = new PlayerSession(session.getId(), session);
        PlayerSession player2Session = gameSession.get(playerSession);
        gameSession.remove(playerSession);
        if (player2Session.getId() != null) {
            gameSession.put(player2Session, new PlayerSession(null, null));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        PlayerSession playerSession = new PlayerSession(session.getId(), session);
        PlayerSession player2 = gameSession.get(playerSession);
        if (player2.getId() != null) {
            boolean matchFinished = service.isMatchFinished(mapper.readValue(message.getPayload(), Answer.class));
            if (matchFinished) {
                var victoryPayload = mapper.writeValueAsString(new SimpleMessage("You Win!"));
                playerSession.getSession().sendMessage(new TextMessage(
                        mapper.writeValueAsString(new WinMessage(victoryPayload))));

                var lostPayload = mapper.writeValueAsString(new SimpleMessage("You Lose!"));
                player2.getSession().sendMessage(new TextMessage(
                        mapper.writeValueAsString(new LoseMessage(lostPayload))));
            } else {
                player2.getSession().sendMessage(new TextMessage(
                        mapper.writeValueAsString(new PlayerAnswerMessage(message.getPayload()))));
            }
        } else {
            var payload = mapper.writeValueAsString(new SimpleMessage("There are no other players around, wait for other player connection"));
            session.sendMessage(new TextMessage(
                    mapper.writeValueAsString(new NoPlayerAroundMessage(payload))));
        }
    }
}