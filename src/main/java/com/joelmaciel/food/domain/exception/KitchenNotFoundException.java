package com.joelmaciel.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KitchenNotFoundException extends RuntimeException {

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long kitchenId) {
        this(String.format("Kitchen not found for this id %d : ", kitchenId));
    }
}
