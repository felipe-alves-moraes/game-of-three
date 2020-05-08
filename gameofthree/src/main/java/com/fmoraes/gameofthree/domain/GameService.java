package com.fmoraes.gameofthree.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmoraes.gameofthree.domain.entity.Answer;
import org.springframework.stereotype.Component;

@Component
public class GameService {
    private final ObjectMapper objectMapper;

    public GameService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean isMatchFinished(Answer answer) {
        return answer.getResult() == 1;
    }
}
