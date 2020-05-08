package com.fmoraes.gameofthree.config;

import com.fmoraes.gameofthree.domain.*;
import com.fmoraes.gameofthree.repository.Repository;
import com.fmoraes.gameofthree.service.GameModeAutomatic;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebSocketClient {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final String uri;
    private final Repository<Message> repository;
    private final Gson gson = new Gson();
    private final WebSocket webSocket;
    private GameModeAutomatic automatic;

    public WebSocketClient(String uri, Repository<Message> repository) {
        this.uri = uri;
        this.repository = repository;
        this.webSocket = connectToGameServer();
    }

    public void addAutomaticEngine(GameModeAutomatic automatic) {
        this.automatic = automatic;
    }

    public void sendMessage(Message message) {
        webSocket.sendText(gson.toJson(message), true);
    }

    public void close() {
        CompletableFuture<WebSocket> sendClose = webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok");
        sendClose.thenRun(() -> System.out.println("Thanks for playing"));
        executor.shutdown();
    }

    private WebSocket connectToGameServer() {
        HttpClient httpClient = HttpClient.newBuilder().executor(executor).build();

        WebSocket.Builder webSocketBuilder = httpClient.newWebSocketBuilder();
        CompletableFuture<WebSocket> webSocketFuture = webSocketBuilder
                .buildAsync(URI.create(uri), new WebSocketListener());

        System.out.println("Connected to server");
        return webSocketFuture.join();
    }

    class WebSocketListener implements WebSocket.Listener {
        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            return CompletableFuture
                    .supplyAsync(() -> gson.fromJson(String.valueOf(data), ServerPayload.class))
                    .thenApply(Factory::createGameMessage)
                    .<CompletionStage<?>>thenApply(message -> {
                        System.out.println("You received:" + " " + message);
                        if (message instanceof Message) {
                            var newMessage = (Message) message;
                            repository.add(newMessage);
                            if (ConfigurationHandler.isPlayingAutomatic()) {
                                WebSocketClient.this.automatic.makeNewMove(newMessage);
                                return WebSocket.Listener.super.onText(webSocket, data, last);
                            }
                        } else if (message instanceof EndGameMessage) {
                            System.out.println(message);
                            System.out.println("Type quit to exit");
                            return null;
                        }

                        System.out.println("What are you going to do?");
                        return WebSocket.Listener.super.onText(webSocket, data, last);
                    })
                    .exceptionally(throwable -> {
                        System.out.println(throwable.getMessage());
                        return WebSocket.Listener.super.onText(webSocket, data, last);
                    });
        }
    }
}
