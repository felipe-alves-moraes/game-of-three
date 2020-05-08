package com.fmoraes.gameofthree.service;

import com.fmoraes.gameofthree.config.WebSocketClient;
import com.fmoraes.gameofthree.domain.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GameModeAutomaticTest {

    private GameModeAutomatic gameModeAutomatic;
    private WebSocketClient webSocketClient;
    private GameRules gameRules;

    @BeforeEach
    public void init(){
        webSocketClient = Mockito.mock(WebSocketClient.class);
        gameRules = Mockito.mock(GameRules.class);
        gameModeAutomatic = new GameModeAutomatic(webSocketClient, gameRules);
    }

    @Test
    public void shouldAddZeroIfAlreadyDivisibleBy3() {
        Message message = new Message(0, 57);
        ArgumentCaptor<String> addedValueCapture = ArgumentCaptor.forClass(String.class);

        gameModeAutomatic.makeNewMove(message);

        Mockito.verify(gameRules, Mockito.atMostOnce()).createMessage(addedValueCapture.capture());
        Mockito.verify(webSocketClient, Mockito.atMostOnce()).sendMessage(Mockito.any());

        Integer addedValue = Integer.parseInt(addedValueCapture.getValue());
        assertEquals(addedValue, 0);
    }

    @Test
    public void shouldAddOneIfAlreadyDivisibleBy3() {
        Message message = new Message(0, 56);
        ArgumentCaptor<String> addedValueCapture = ArgumentCaptor.forClass(String.class);

        gameModeAutomatic.makeNewMove(message);

        Mockito.verify(gameRules, Mockito.atMostOnce()).createMessage(addedValueCapture.capture());
        Mockito.verify(webSocketClient, Mockito.atMostOnce()).sendMessage(Mockito.any());

        Integer addedValue = Integer.parseInt(addedValueCapture.getValue());
        assertEquals(addedValue, 1);
    }

    @Test
    public void shouldSubtractOneIfAlreadyDivisibleBy3() {
        Message message = new Message(0, 19);
        ArgumentCaptor<String> addedValueCapture = ArgumentCaptor.forClass(String.class);

        gameModeAutomatic.makeNewMove(message);

        Mockito.verify(gameRules, Mockito.atMostOnce()).createMessage(addedValueCapture.capture());
        Mockito.verify(webSocketClient, Mockito.atMostOnce()).sendMessage(Mockito.any());

        Integer addedValue = Integer.parseInt(addedValueCapture.getValue());
        assertEquals(addedValue, -1);
    }

}