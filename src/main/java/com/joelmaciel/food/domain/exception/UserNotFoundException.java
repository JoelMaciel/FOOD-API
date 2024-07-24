package com.joelmaciel.food.domain.exception;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("There is no user with id %d saved in the database.", userId));
    }
}
