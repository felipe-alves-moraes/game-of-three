package com.fmoraes.gameofthree.websocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

public class PlayerSession {
    private String id;
    private WebSocketSession session;

    public PlayerSession(String id, WebSocketSession session) {
        this.id = id;
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public WebSocketSession getSession() {
        return session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSession that = (PlayerSession) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
