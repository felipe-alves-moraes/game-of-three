package com.fmoraes.gameofthree.service;

import com.fmoraes.gameofthree.config.ConfigurationHandler;
import com.fmoraes.gameofthree.config.WebSocketClient;
import com.fmoraes.gameofthree.domain.Message;
import com.fmoraes.gameofthree.repository.Repository;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameService {

    private final Repository<Message> repository;

    public GameService(Repository<Message> repository) {
        this.repository = repository;
    }

    public void start() {
        var webSocketClient = new WebSocketClient("ws://localhost:8080/game", repository);
        var rules = new GameRules(repository);
        if (ConfigurationHandler.isPlayingAutomatic()) {
            webSocketClient.addAutomaticEngine(new GameModeAutomatic(webSocketClient, rules));
        }

        String value;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Send your number: ");
        do {
            value = scanner.nextLine();
            if (value.equalsIgnoreCase("quit")) {
                break;
            }
            Message message = rules.createMessage(value);
            if (message == null) {
                continue;
            }
            webSocketClient.sendMessage(message);

        } while (true);

        webSocketClient.close();
        System.exit(0);
    }

    public void manualMode(WebSocketClient webSocketClient, GameRules rules,
                           Scanner scanner) {


    }
}
