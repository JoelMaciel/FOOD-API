package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RestaurantService {

    List<RestaurantDTO> findAll();

    RestaurantDTO findById(Long restaurantId);

    RestaurantDTO save(RestaurantRequestDTO restaurantRequestDTO);

    RestaurantDTO update(Long restaurantId, RestaurantRequestDTO restaurantRequestDTO);

    Restaurant optinalRestaurant(Long restaurantId);

    void activate(Long restaurantId);

    void inactivate(Long restaurantId);

    Page<PaymentMethodDTO> findPaymentMethods(Pageable pageable, Long restaurantId);

    void deassociatePaymentMethods(Long restaurantId, Long paymentMethodId);

    void associatePaymentMethods(Long restaurantId, Long paymentMethodId);

    void open(Long restaurantId);

    void close(Long restaurantId);

    Page<UserDTO> findAllResponsible(Long restaurantId, Pageable pageable);

    void associateUser(Long restaurantId, Long userId);

    void disassociateUser(Long restaurantId, Long userId);
}
