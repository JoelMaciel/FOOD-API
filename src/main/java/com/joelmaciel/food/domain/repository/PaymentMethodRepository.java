package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> findAll();

    PaymentMethod findById(Long id);

    PaymentMethod save(PaymentMethod paymentMethod);

    void remove(PaymentMethod paymentMethod);
}
