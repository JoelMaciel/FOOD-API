package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.exception.RestuarantNotFoundException;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.repository.RestaurantRepository;
import com.joelmaciel.food.domain.service.KitchenService;
import com.joelmaciel.food.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenService kitchenService;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        return restaurantById(restaurantId);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = kitchenService.findById(restaurant.getKitchen().getId());
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Long restaurantId, Restaurant restaurantRequest) {
        Restaurant restaurant = restaurantById(restaurantId);
        Kitchen kitchen = kitchenService.findById(restaurantRequest.getKitchen().getId());

        return updateRestaurantBuilder(restaurantRequest, restaurant, kitchen);
    }

    public Restaurant restaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestuarantNotFoundException(restaurantId));
    }

    private Restaurant updateRestaurantBuilder(Restaurant restaurantRequest, Restaurant restaurant, Kitchen kitchen) {
        restaurant = Restaurant.builder()
                .id(restaurant.getId())
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .kitchen(kitchen)
                .build();

        return restaurantRepository.save(restaurant);
    }
}
