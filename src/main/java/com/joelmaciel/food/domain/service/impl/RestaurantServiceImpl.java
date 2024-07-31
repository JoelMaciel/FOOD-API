package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.PaymentMethodConverter;
import com.joelmaciel.food.api.dto.converter.RestaurantConverter;
import com.joelmaciel.food.api.dto.converter.UserConverter;
import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.RestaurantNotFoundException;
import com.joelmaciel.food.domain.model.*;
import com.joelmaciel.food.domain.repository.RestaurantRepository;
import com.joelmaciel.food.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    public static final String MSD_KITCHEN_NOT_FOUND = "There is no saved kitchen with this id";
    private final RestaurantRepository restaurantRepository;
    private final KitchenService kitchenService;
    private final CityService cityService;
    private final PaymentMethodService paymentMethodService;
    private final UserService userService;

    @Override
    public List<RestaurantDTO> findAll() {
        return RestaurantConverter.toDTOList(restaurantRepository.findAll());
    }

    @Override
    public Page<PaymentMethodDTO> findPaymentMethods(Pageable pageable, Long restaurantId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        Set<PaymentMethod> paymentMethods = restaurant.getPaymentMethods();
        return PaymentMethodConverter.setToPageDTO(paymentMethods, pageable);
    }

    @Override
    public RestaurantDTO findById(Long restaurantId) {
        return RestaurantConverter.toDTO(optinalRestaurant(restaurantId));
    }

    @Override
    @Transactional
    public RestaurantDTO save(RestaurantRequestDTO restaurantRequestDTO) {
        try {
            City city = cityService.optionalCity(restaurantRequestDTO.getAddress().getCity().getCityId());
            Restaurant restaurant = RestaurantConverter.toEntity(restaurantRequestDTO, city);
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
        City city = cityService.optionalCity(restaurantRequestDTO.getAddress().getCity().getCityId());

        Restaurant updateRestaurant = RestaurantConverter.updateRestaurant(restaurantRequestDTO, restaurant, city);
        updateRestaurant.setKitchen(kitchen);
        updateRestaurant.getAddress().getCity().setId(city.getId());

        return RestaurantConverter.toDTO(restaurantRepository.save(updateRestaurant));
    }

    @Transactional
    @Override
    public void activate(Long restaurantId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        restaurant.activate();
    }

    @Transactional
    @Override
    public void inactivate(Long restaurantId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        restaurant.inactivate();
    }

    @Transactional
    @Override
    public void deassociatePaymentMethods(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.optionalPaymentMethod(paymentMethodId);

        restaurant.removePaymentMethod(paymentMethod);
    }

    @Transactional
    @Override
    public void associatePaymentMethods(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.optionalPaymentMethod(paymentMethodId);

        restaurant.associatePaymentMethod(paymentMethod);
    }

    @Transactional
    @Override
    public void open(Long restaurantId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        restaurant.open();
    }

    @Transactional
    @Override
    public void close(Long restaurantId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        restaurant.close();
    }

    @Override
    public Page<UserDTO> findAllResponsible(Long restaurantId, Pageable pageable) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        Set<User> responsible = restaurant.getResponsible();
        return UserConverter.setToPageDTO(responsible, pageable);
    }

    @Transactional
    @Override
    public void associateUser(Long restaurantId, Long userId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        User use = userService.optionalUser(userId);
        restaurant.addResponsible(use);
    }

    @Transactional
    @Override
    public void disassociateUser(Long restaurantId, Long userId) {
        Restaurant restaurant = optinalRestaurant(restaurantId);
        User use = userService.optionalUser(userId);
        restaurant.removeResponsible(use);
    }

    @Transactional
    @Override
    public void activeSeveralRestaurants(List<Long> restaurantIds) {
        try {
            restaurantIds.forEach(this::activate);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void inactiveSeveralRestaurants(List<Long> restaurantIds) {
        try {
            restaurantIds.forEach(this::inactivate);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Restaurant optinalRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
