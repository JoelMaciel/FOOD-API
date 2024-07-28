package com.joelmaciel.food.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId, Long restaurantId) {
        this(String.format("There is no product %d registered for restaurant %d", productId, restaurantId));
    }
}
