package com.fmoraes.gameofthree.domain.entity;

public class Answer {

    private Integer added;
    private Integer result;

    public Answer(Integer added, Integer result) {
        this.added = added;
        this.result = result;
    }

    public Integer getAdded() {
        return added;
    }

    public Integer getResult() {
        return result;
    }
}
