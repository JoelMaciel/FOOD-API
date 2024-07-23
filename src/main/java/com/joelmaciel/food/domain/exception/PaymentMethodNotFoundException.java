package com.joelmaciel.food.domain.exception;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    public PaymentMethodNotFoundException(Long paymentMethodId) {
        this(String.format("PaymentMethod of id %d not found", paymentMethodId));
    }
}

