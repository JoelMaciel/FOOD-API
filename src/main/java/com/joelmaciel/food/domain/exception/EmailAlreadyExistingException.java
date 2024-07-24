package com.joelmaciel.food.domain.exception;

public class EmailAlreadyExistingException extends EntityInUseException {

    public EmailAlreadyExistingException(String email) {
        super(String.format("Email %s already registered in the database", email));
    }
}
