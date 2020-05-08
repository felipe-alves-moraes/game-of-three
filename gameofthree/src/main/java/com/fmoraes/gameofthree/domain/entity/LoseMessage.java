package com.fmoraes.gameofthree.domain.entity;

public class LoseMessage implements GameEvents {
    private final String payload;

    public LoseMessage(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return "LOSE";
    }

    public String getPayload() {
        return payload;
    }
}
