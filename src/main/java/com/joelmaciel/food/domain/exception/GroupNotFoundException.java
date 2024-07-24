package com.joelmaciel.food.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException {

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("There is no group with id %d saved in the database", groupId));
    }
}
