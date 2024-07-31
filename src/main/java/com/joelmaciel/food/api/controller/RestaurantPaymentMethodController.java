package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants/{restaurantId}/payment-method")
public class RestaurantPaymentMethodController {

    private final RestaurantService restaurantService;

    @GetMapping
    public Page<PaymentMethodDTO> findAll(
            @PageableDefault(page = 0, size = 10, sort = "restaurantId", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long restaurantId) {
        return restaurantService.findPaymentMethods(pageable, restaurantId);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.deassociatePaymentMethods(restaurantId, paymentMethodId);
    }

    @PutMapping("/{paymentMethodId}")
    public void associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.associatePaymentMethods(restaurantId, paymentMethodId);
    }
}
