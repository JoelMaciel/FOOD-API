package com.joelmaciel.food.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long kitchenId) {
        this(String.format("Kitchen not found for this id %d : ", kitchenId));
    }
}
