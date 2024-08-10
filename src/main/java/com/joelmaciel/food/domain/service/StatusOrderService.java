package com.joelmaciel.food.domain.service;

public interface StatusOrderService {

    void confirm(String codeOrder);

    void deliver(String codeOrder);

    void  cancel(String codeOrder);
}
