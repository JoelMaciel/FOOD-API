package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.enums.OrderStatus;
import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.model.Order;
import com.joelmaciel.food.domain.service.OrderService;
import com.joelmaciel.food.domain.service.StatusOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class StatusOrderServiceImpl implements StatusOrderService {

    private static final String CANNOT_BE_CHANGED_FROM = "Order status %d cannot be changed from %s to %s";

    private final OrderService orderService;

    @Transactional
    @Override
    public void confirm(Long orderId) {
        updateOrderStatus(orderId, OrderStatus.CREATED, OrderStatus.CONFIRMED,
                OffsetDateTime.now(), null, null);
    }

    @Transactional
    @Override
    public void deliver(Long orderId) {
        updateOrderStatus(orderId, OrderStatus.CONFIRMED,
                OrderStatus.DELIVERED, null,
                OffsetDateTime.now(), null);
    }

    @Transactional
    @Override
    public void cancel(Long orderId) {
        updateOrderStatus(orderId, OrderStatus.CREATED, OrderStatus.CANCELLED,
                null, null, OffsetDateTime.now());
    }

    private void updateOrderStatus(Long orderId, OrderStatus requiredStatus,
                                   OrderStatus newStatus, OffsetDateTime confirmationDate,
                                   OffsetDateTime deliveryDate, OffsetDateTime cancellationDate) {
        Order order = orderService.optionalOrder(orderId);

        if (!order.getStatus().equals(requiredStatus)) {
            throw new BusinessException(String.format(
                    CANNOT_BE_CHANGED_FROM, order.getId(),
                    order.getStatus().getDescription(), newStatus.getDescription()));
        }

        order.setStatus(newStatus);

        if (confirmationDate != null) {
            order.setConfirmationDate(confirmationDate);
        }
        if (deliveryDate != null) {
            order.setDeliveryDate(deliveryDate);
        }
        if (cancellationDate != null) {
            order.setCancellationDate(cancellationDate);
        }
    }
}
