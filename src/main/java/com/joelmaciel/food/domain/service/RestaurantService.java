package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.domain.model.Restaurant;

import java.util.List;


public interface RestaurantService {

    List<RestaurantDTO> findAll();

    RestaurantDTO findById(Long restaurantId);

    RestaurantDTO save(RestaurantRequestDTO restaurantRequestDTO);

    RestaurantDTO update(Long restaurantId, RestaurantRequestDTO restaurantRequestDTO);
}
