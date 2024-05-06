package com.joelmaciel.food.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("State with id %d does not exist", stateId));
    }
}
