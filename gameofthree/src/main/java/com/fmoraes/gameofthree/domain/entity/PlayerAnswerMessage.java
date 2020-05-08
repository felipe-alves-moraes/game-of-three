package com.fmoraes.gameofthree.domain.entity;

public class PlayerAnswerMessage implements GameEvents {
    private final String payload;

    public PlayerAnswerMessage(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return "ANSWER";
    }

    public String getPayload() {
        return payload;
    }
}
