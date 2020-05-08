package com.fmoraes.gameofthree.domain;

import com.google.gson.Gson;

public final class Factory {
    private static final Gson gson = new Gson();

    private Factory(){}

    public static ServerEvents createGameMessage(ServerPayload payload) {
        switch (payload.getType()) {
            case "ANSWER": return gson.fromJson(payload.getPayload(), Message.class);
            case "NO_PLAYER": return gson.fromJson(payload.getPayload(), SimpleMessage.class);
            case "WIN":
            case "LOSE": return gson.fromJson(payload.getPayload(), EndGameMessage.class);
        }
        throw new UnsupportedOperationException("Type not recognized!");
    }
}
