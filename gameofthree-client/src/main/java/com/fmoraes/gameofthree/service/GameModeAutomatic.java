package com.fmoraes.gameofthree.service;

import com.fmoraes.gameofthree.config.WebSocketClient;
import com.fmoraes.gameofthree.domain.Message;

public class GameModeAutomatic {
    private final WebSocketClient webSocketClient;
    private final GameRules gameRules;

    public GameModeAutomatic(WebSocketClient webSocketClient, GameRules gameRules) {
        this.webSocketClient = webSocketClient;
        this.gameRules = gameRules;
    }

    public void makeNewMove(Message message) {
        int added;
        if (message.getResult() % 3 == 0) {
            added = 0;
        } else if ((message.getResult() + 1) % 3 == 0) {
            added = 1;
        } else {
            added = -1;
        }
        Message newMessage = gameRules.createMessage(String.valueOf(added));
        webSocketClient.sendMessage(newMessage);
    }
}
