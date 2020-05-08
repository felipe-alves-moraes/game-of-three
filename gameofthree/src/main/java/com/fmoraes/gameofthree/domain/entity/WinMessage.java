package com.fmoraes.gameofthree.domain.entity;

public class WinMessage implements GameEvents {
    private final String payload;

    public WinMessage(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return "WIN";
    }

    public String getPayload() {
        return payload;
    }
}
