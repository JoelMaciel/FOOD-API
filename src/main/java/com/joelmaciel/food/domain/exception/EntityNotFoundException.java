package com.joelmaciel.food.domain.exception;

public abstract class EntityNotFoundException extends BusinessException {

    protected EntityNotFoundException(String message) {
        super(message);
    }

}
