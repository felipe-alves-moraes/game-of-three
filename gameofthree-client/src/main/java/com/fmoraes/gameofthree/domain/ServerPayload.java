package com.fmoraes.gameofthree.domain;

public class ServerPayload {
    private final String type;
    private final String payload;

    public ServerPayload(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }
}
