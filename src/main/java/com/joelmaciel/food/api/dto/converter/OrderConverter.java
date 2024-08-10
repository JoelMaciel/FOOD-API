package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.model.Order;
import org.springframework.data.domain.Page;

public class OrderConverter {

    private OrderConverter() {
    }

    public static Page<OrderSummaryDTO> orderDTOPage(Page<Order> orderPage) {
        return orderPage.map(OrderConverter::toSummaryDTO);
    }

    public static OrderSummaryDTO toSummaryDTO(Order order) {
        return OrderSummaryDTO.builder()
                .id(order.getId())
                .subTotal(order.getSubTotal())
                .freightRate(order.getFreightRate())
                .totalValue(order.getTotalValue())
                .status(order.getStatus())
                .creationDate(order.getCreationDate())
                .restaurant(RestaurantConverter.toRestaurantSummaryDTO(order.getRestaurant()))
                .client(UserConverter.toDTO(order.getClient()))
                .build();
    }

    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .subTotal(order.getSubTotal())
                .freightRate(order.getFreightRate())
                .totalValue(order.getTotalValue())
                .status(order.getStatus())
                .creationDate(order.getCreationDate())
                .paymentMethod(PaymentMethodConverter.toDTO(order.getPaymentMethod()))
                .confirmationDate(order.getConfirmationDate())
                .deliveryDate(order.getDeliveryDate())
                .cancellationDate(order.getCancellationDate())
                .restaurant(RestaurantConverter.toRestaurantSummaryDTO(order.getRestaurant()))
                .addressDelivery(AddressConverter.toDTO(order.getAddressDelivery()))
                .client(UserConverter.toDTO(order.getClient()))
                .items(OrderItemConverter.toListDTO(order.getItems()))
                .build();
    }

}
