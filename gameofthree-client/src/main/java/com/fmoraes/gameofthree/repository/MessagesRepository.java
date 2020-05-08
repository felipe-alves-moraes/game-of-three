package com.fmoraes.gameofthree.repository;

import com.fmoraes.gameofthree.domain.Message;

import java.util.LinkedList;
import java.util.Optional;

public class MessagesRepository implements Repository<Message> {
    private final LinkedList<Message> messages = new LinkedList<>();

    public Optional<Message> getLast() {
        return Optional.ofNullable(messages.peekLast());
    }

    public void add(Message message) {
        messages.add(message);
    }
}
