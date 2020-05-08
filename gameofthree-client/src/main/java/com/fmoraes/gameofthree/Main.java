package com.fmoraes.gameofthree;

import com.fmoraes.gameofthree.repository.MessagesRepository;
import com.fmoraes.gameofthree.service.GameService;

public class Main {

    public static void main(String[] args) {
        new GameService(new MessagesRepository()).start();
    }
}
