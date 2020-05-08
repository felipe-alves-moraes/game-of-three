package com.fmoraes.gameofthree.domain;

public class SimpleMessage implements ServerEvents {
    private final String message;

    public SimpleMessage(String message) {
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
