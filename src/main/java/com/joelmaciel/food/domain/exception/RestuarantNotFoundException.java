package com.joelmaciel.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestuarantNotFoundException extends RuntimeException {

    public RestuarantNotFoundException(String message) {
        super(message);
    }

    public RestuarantNotFoundException(Long restaurantId) {
        this(String.format("Restaurant not found for this id %d : ", restaurantId));
    }
}
