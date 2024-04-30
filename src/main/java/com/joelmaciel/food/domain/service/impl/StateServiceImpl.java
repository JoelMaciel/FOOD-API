package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.repository.StateRepository;
import com.joelmaciel.food.domain.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }
}
