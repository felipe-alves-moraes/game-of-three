package com.fmoraes.gameofthree.service;

import com.fmoraes.gameofthree.domain.Message;
import com.fmoraes.gameofthree.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class GameRules {
    private final List<Integer> validNumbers = new ArrayList<>();
    private final Repository<Message> messagesRepository;

    public GameRules(Repository<Message> messagesRepository) {
        this.messagesRepository = messagesRepository;
        validNumbers.add(-1);
        validNumbers.add(-0);
        validNumbers.add(1);
    }

    public Message createMessage(String message) {
        try {
            int value = Integer.parseInt(message);
            int lastValue = this.messagesRepository.getLast().map(Message::getResult).orElse(0);

            if (lastValue == 0) {
                return new Message(value, value);
            } else if (validNumbers.contains(value)) {
                Message newMessage = new Message(value, (value + lastValue) / 3);
                System.out.println("You are sending: " + newMessage.getResult());
                return newMessage;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid input, please try again!");
        }

        return null;
    }


}
