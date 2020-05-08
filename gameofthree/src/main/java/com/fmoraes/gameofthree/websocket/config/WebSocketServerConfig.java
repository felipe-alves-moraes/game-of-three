package com.fmoraes.gameofthree.websocket.config;

import com.fmoraes.gameofthree.websocket.listener.GameListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {

    private final GameListener gameListener;

    public WebSocketServerConfig(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(gameListener, "/game")
                .setAllowedOrigins("*");
    }

}
