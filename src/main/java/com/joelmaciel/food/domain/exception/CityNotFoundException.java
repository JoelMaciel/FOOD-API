package com.joelmaciel.food.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityId) {
        this(String.format("City of id %d not found", cityId));
    }
}
