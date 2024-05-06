package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.RestaurantNotFoundException;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.repository.RestaurantRepository;
import com.joelmaciel.food.domain.service.KitchenService;
import com.joelmaciel.food.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    public static final String MSD_KITCHEN_NOT_FOUND = "There is no saved kitchen with this id";
    private final RestaurantRepository restaurantRepository;
    private final KitchenService kitchenService;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        return optinalRestaurant(restaurantId);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        try {
            return restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(MSD_KITCHEN_NOT_FOUND);
        }
    }

    @Override
    public Restaurant update(Long restaurantId, Restaurant restaurantRequest) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        Kitchen kitchen = kitchenService.findById(restaurantRequest.getKitchen().getId());

        return updateRestaurantBuilder(restaurantRequest, restaurant, kitchen);
    }

    public Restaurant optinalRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    private Restaurant updateRestaurantBuilder(Restaurant restaurantRequest, Restaurant restaurant, Kitchen kitchen) {
        restaurant = Restaurant.builder()
                .id(restaurant.getId())
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .kitchen(kitchen)
                .registrationDate(restaurant.getRegistrationDate())
                .address(restaurant.getAddress())
                .paymentMethods(restaurant.getPaymentMethods())
                .build();

        return restaurantRepository.save(restaurant);
    }
}
