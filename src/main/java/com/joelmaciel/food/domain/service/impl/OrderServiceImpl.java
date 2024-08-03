package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.OrderConverter;
import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.exception.OrderNotFoundException;
import com.joelmaciel.food.domain.model.Order;
import com.joelmaciel.food.domain.repository.OrderRepository;
import com.joelmaciel.food.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Page<OrderSummaryDTO> findAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return OrderConverter.orderDTOPage(orders);
    }

    @Override
    public OrderDTO findById(Long orderId) {
        return OrderConverter.toDTO(optionalOrder(orderId));
    }

    @Override
    public Order optionalOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
