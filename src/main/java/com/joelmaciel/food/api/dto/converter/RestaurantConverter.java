package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.AddressDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.domain.model.Address;
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
                .kitchen(restaurant.getKitchen())
                .address(toAddressDTO(restaurant.getAddress()))
                .build();
    }

    public static Restaurant toEntity(RestaurantRequestDTO restaurantRequestDTO) {
        return Restaurant.builder()
                .name(restaurantRequestDTO.getName())
                .freightRate(restaurantRequestDTO.getFreightRate())
                .build();
    }

    public static Restaurant updateRestaurant(RestaurantRequestDTO restaurantRequest, Restaurant originalRestaurant) {
        return originalRestaurant.toBuilder()
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .updateDate(OffsetDateTime.now())
                .build();
    }

    public static AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .district(address.getDistrict())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .complement(address.getComplement())
                .city(CityConverter.toDTO(address.getCity()))
                .build();
    }
}
