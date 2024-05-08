package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.StateConverter;
import com.joelmaciel.food.api.dto.request.StateRequestDTO;
import com.joelmaciel.food.api.dto.response.StateDTO;
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
    public List<StateDTO> findAll() {
        return StateConverter.toDTOList(stateRepository.findAll());
    }

    @Override
    public StateDTO findById(Long stateId) {
        return StateConverter.toDTO(optionalState(stateId));
    }

    @Override
    @Transactional
    public StateDTO save(StateRequestDTO stateRequest) {
        State state = StateConverter.toEntity(stateRequest);
        return StateConverter.toDTO(stateRepository.save(state));
    }

    @Override
    @Transactional
    public StateDTO update(Long stateId, StateRequestDTO stateRequest) {
        State state = optionalState(stateId);
        state.setName(stateRequest.getName());
        return StateConverter.toDTO(stateRepository.save(state));
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

    @Override
    public State optionalState(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }
}
