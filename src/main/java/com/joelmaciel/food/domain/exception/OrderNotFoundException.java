package com.joelmaciel.food.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException{

     public OrderNotFoundException(String codeOrder ) {
        super(String.format("There is no order with id %s registered in the database", codeOrder));
    }
}
