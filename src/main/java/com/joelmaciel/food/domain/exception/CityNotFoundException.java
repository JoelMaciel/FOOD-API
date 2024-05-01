package com.joelmaciel.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long  cityId) {
        this(String.format("City of id %d not found", cityId));
    }
}
