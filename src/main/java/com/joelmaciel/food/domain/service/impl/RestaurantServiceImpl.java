package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.RestaurantConverter;
import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
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
    public List<RestaurantDTO> findAll() {
        return RestaurantConverter.toDTOList(restaurantRepository.findAll());
    }

    @Override
    public RestaurantDTO findById(Long restaurantId) {
        return RestaurantConverter.toDTO(optinalRestaurant(restaurantId));
    }

    @Override
    @Transactional
    public RestaurantDTO save(RestaurantRequestDTO restaurantRequestDTO) {
        try {
            Restaurant restaurant = RestaurantConverter.toEntity(restaurantRequestDTO);
            Kitchen kitchen = kitchenService.optionalKitchen(restaurantRequestDTO.getKitchenId());
            restaurant.setKitchen(kitchen);
            return RestaurantConverter.toDTO(restaurantRepository.save(restaurant));
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(MSD_KITCHEN_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public RestaurantDTO update(Long restaurantId, RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        Kitchen kitchen = kitchenService.optionalKitchen(restaurantRequestDTO.getKitchenId());

        Restaurant updateRestaurant = RestaurantConverter.updateRestaurant(restaurantRequestDTO, restaurant); // Passa o restaurante original
        updateRestaurant.setKitchen(kitchen);

        return RestaurantConverter.toDTO(restaurantRepository.save(updateRestaurant));
    }

    public Restaurant optinalRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
