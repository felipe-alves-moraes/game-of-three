package com.fmoraes.gameofthree.service;

import com.fmoraes.gameofthree.domain.Message;
import com.fmoraes.gameofthree.repository.MessagesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameRulesTest {

    private GameRules gameRules;
    private MessagesRepository repository;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(MessagesRepository.class);
        gameRules = new GameRules(repository);
    }

    @Test
    public void shouldCreateInitialMessage() {
        Mockito.when(repository.getLast()).thenReturn(Optional.empty());

        Message message = gameRules.createMessage("56");

        assertEquals(message.getAdded(), 56);
        assertEquals(message.getResult(), 56);
    }

    @Test
    public void shouldCreateMessageWithAddedValueAndResultDividedBy3() {
        Mockito.when(repository.getLast()).thenReturn(Optional.of(new Message(56, 56)));

        Message message = gameRules.createMessage("1");

        assertEquals(message.getAdded(), 1);
        assertEquals(message.getResult(), 19);
    }

    @Test
    public void shouldReturnNullWhenReceivingInvalidInput() {
        Mockito.when(repository.getLast()).thenReturn(Optional.of(new Message(56, 56)));

        Message nullMessage = gameRules.createMessage("5");

        assertNull(nullMessage);
    }

    @Test
    public void shouldReturnNullWhenReceivingNullInput() {
        Mockito.when(repository.getLast()).thenReturn(Optional.of(new Message(56, 56)));

        Message nullMessage = gameRules.createMessage(null);

        assertNull(nullMessage);
    }

}