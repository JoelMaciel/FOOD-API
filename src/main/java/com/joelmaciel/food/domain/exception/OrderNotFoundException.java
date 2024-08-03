package com.joelmaciel.food.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException{

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        this(String.format("There is no order with id %d registered in the database", orderId));
    }
}
