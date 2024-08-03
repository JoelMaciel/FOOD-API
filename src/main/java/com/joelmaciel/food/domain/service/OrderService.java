package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<OrderSummaryDTO> findAll(Pageable pageable);

    OrderDTO findById(Long orderId);

    Order optionalOrder(Long orderId);
}
