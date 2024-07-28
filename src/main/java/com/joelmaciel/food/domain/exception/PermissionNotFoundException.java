package com.joelmaciel.food.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException{

    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("Permission id %d not found.", permissionId));
    }
}
