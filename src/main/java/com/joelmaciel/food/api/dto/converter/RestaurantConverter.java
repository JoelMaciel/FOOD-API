package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.domain.model.Address;
import com.joelmaciel.food.domain.model.City;
import com.joelmaciel.food.domain.model.Restaurant;

import java.time.OffsetDateTime;
import java.util.List;

public class RestaurantConverter {
    private RestaurantConverter() {
    }

    public static List<RestaurantDTO> toDTOList(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantConverter::toDTO)
                .toList();
    }

    public static RestaurantDTO toDTO(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .freightRate(restaurant.getFreightRate())
                .active(restaurant.getActive())
                .open(restaurant.getOpen())
                .kitchen(restaurant.getKitchen())
                .address(AddressConverter.toDTO(restaurant.getAddress()))
                .build();
    }

    public static Restaurant toEntity(RestaurantRequestDTO restaurantRequestDTO, City city) {
        return Restaurant.builder()
                .name(restaurantRequestDTO.getName())
                .freightRate(restaurantRequestDTO.getFreightRate())
                .active(true)
                .address(AddressConverter.toEntity(restaurantRequestDTO.getAddress(), city))
                .build();
    }

    public static Restaurant updateRestaurant(
            RestaurantRequestDTO restaurantRequest,
            Restaurant originalRestaurant,
            City city
    ) {
        Address address = AddressConverter.toEntity(restaurantRequest.getAddress(), city);
        return originalRestaurant.toBuilder()
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .address(address)
                .updateDate(OffsetDateTime.now())
                .build();
    }
}
