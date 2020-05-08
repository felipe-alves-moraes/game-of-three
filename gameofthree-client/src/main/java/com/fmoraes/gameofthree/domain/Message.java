package com.fmoraes.gameofthree.domain;

public class Message implements ServerEvents {
    private final Integer added;
    private final Integer result;

    public Message(Integer added, Integer result) {
        this.added = added;
        this.result = result;
    }

    public Integer getAdded() {
        return added;
    }

    public Integer getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "added=" + added +
                ", result=" + result +
                '}';
    }
}
