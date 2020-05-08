package com.fmoraes.gameofthree.domain;

public class EndGameMessage implements ServerEvents {
    private final String message;

    public EndGameMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
