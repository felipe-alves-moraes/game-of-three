package com.fmoraes.gameofthree.config;

public class ConfigurationHandler {

    public static boolean isPlayingAutomatic() {
        var isAutomatic = System.getenv("game_mode_automatic");
        return Boolean.parseBoolean(isAutomatic);
    }
}
