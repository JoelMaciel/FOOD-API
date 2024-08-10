package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.response.OrderItemDTO;
import com.joelmaciel.food.domain.model.OrderItem;

import java.util.List;

public class OrderItemConverter {

    private OrderItemConverter() {
    }

    public static List<OrderItemDTO> toListDTO(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItemConverter::toDTO)
                .toList();
    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .unitPrice(orderItem.getUnitPrice())
                .totalPrice(orderItem.getTotalPrice())
                .quantity(orderItem.getQuantity())
                .observation(orderItem.getObservation())
                .build();
    }
}
