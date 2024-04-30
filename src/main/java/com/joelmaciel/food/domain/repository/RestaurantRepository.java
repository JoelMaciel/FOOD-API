package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();
    Restaurant findById(Long id);
    Restaurant save(Restaurant restaurant);
    void remove(Restaurant restaurant);
}
