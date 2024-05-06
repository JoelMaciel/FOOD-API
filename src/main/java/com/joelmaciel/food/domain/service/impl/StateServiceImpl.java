package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.exception.StateNotFoundException;
import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.repository.StateRepository;
import com.joelmaciel.food.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {
    public static final String STATE_IN_USE = "State cannot be excluded as it is in use";
    private final StateRepository stateRepository;

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State findById(Long stateId) {
        return optionalState(stateId);
    }

    @Override
    @Transactional
    public State save(State stateRequest) {
        return stateRepository.save(stateRequest);
    }

    @Override
    @Transactional
    public State update(Long stateId, State stateRequest) {
        State state = optionalState(stateId);
        state.setName(stateRequest.getName());
        return save(state);
    }

    @Override
    @Transactional
    public void remove(Long stateId) {
        optionalState(stateId);
        try {
            stateRepository.deleteById(stateId);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(STATE_IN_USE);
        }
    }

    public State optionalState(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }
}
