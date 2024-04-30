package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.domain.model.Restaurant;

import java.util.List;


public interface RestaurantService {

    List<Restaurant> findAll();

    Restaurant findById(Long restaurantId);

    Restaurant save(Restaurant restaurant);

    Restaurant update(Long restaurantId, Restaurant restaurantRequest);
}
