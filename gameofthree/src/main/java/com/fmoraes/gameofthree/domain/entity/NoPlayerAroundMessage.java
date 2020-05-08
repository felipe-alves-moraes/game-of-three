package com.fmoraes.gameofthree.domain.entity;

public class NoPlayerAroundMessage implements GameEvents {
    private final String payload;

    public NoPlayerAroundMessage(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return "NO_PLAYER";
    }

    public String getPayload() {
        return payload;
    }
}
