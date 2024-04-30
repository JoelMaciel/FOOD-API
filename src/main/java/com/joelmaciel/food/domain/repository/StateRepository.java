package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> findAll();

    State findById(Long id);

    State save(State state);

    void remove(State state);
}
