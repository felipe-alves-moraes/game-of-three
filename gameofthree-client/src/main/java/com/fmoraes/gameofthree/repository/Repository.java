package com.fmoraes.gameofthree.repository;

import java.util.Optional;

public interface Repository<T> {

    Optional<T> getLast();
    void add(T t);
}
