package com.joelmaciel.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String message) {
        super(message);
    }
}
