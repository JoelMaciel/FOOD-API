package com.joelmaciel.food.domain.exception;

public class PhotoProductNotFoundException extends EntityNotFoundException{

    public PhotoProductNotFoundException(String message) {
        super(message);
    }

    public PhotoProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("There is no photo registration for the product with code %d for the restaurant with code %d",
                restaurantId, productId));
    }

}
