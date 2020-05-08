package com.fmoraes.gameofthree.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationHandlerTest {

    @BeforeEach
    public void init() {
        System.clearProperty("game.mode.automatic");
    }

    @Test
    public void shouldReturnFalseWhenNoConfigProperty() {
        assertFalse(ConfigurationHandler.isPlayingAutomatic());
    }

    @Test
    public void shouldReturnTrueWhenConfigured() {
        System.setProperty("game.mode.automatic", "true");
        assertTrue(ConfigurationHandler.isPlayingAutomatic());
    }

    @Test
    public void shouldReturnFalseWhenConfigured() {
        System.setProperty("game.mode.automatic", "false");
        assertFalse(ConfigurationHandler.isPlayingAutomatic());
    }
}