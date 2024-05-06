package com.joelmaciel.food.domain.exception;

public class EntityInUseException extends BusinessException {

    public EntityInUseException(String message) {
        super(message);
    }
}
