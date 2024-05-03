package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.domain.model.State;

import java.util.List;

public interface StateService {

    List<State> findAll();

    State findById(Long stateId);

    State save(State stateRequest);

    State update(Long stateId, State stateRequest);

    void remove(Long stateId);

}
