package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.StateRequestDTO;
import com.joelmaciel.food.api.dto.response.StateDTO;
import com.joelmaciel.food.domain.model.State;

import java.util.List;

public class StateConverter {
    private StateConverter() {
    }

    public static List<StateDTO> toDTOList(List<State> states) {
        return states.stream()
                .map(StateConverter::toDTO)
                .toList();
    }

    public static StateDTO toDTO(State state) {
        return StateDTO.builder()
                .id(state.getId())
                .name(state.getName())
                .build();
    }

    public static State toEntity(StateRequestDTO stateRequestDTO) {
        return State.builder()
                .name(stateRequestDTO.getName())
                .build();
    }
}
