package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.StateRequestDTO;
import com.joelmaciel.food.api.dto.response.StateDTO;
import com.joelmaciel.food.domain.model.State;

import java.util.List;

public interface StateService {

    List<StateDTO> findAll();

    StateDTO findById(Long stateId);

    StateDTO save(StateRequestDTO stateRequest);

    StateDTO update(Long stateId, StateRequestDTO stateRequest);

    void remove(Long stateId);

    State optionalState(Long stateId);

}
