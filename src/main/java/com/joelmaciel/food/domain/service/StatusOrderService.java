package com.joelmaciel.food.domain.service;

public interface StatusOrderService {

    void confirm(Long orderId);

    void deliver(Long orderId);

    void  cancel(Long orderId);
}
