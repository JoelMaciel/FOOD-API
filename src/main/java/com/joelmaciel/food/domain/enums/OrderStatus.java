package com.joelmaciel.food.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED("Created"),
    CONFIRMED("Confirmed"),
    DELIVERED("Delivered"),
    CANCELLED("Canceled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}
