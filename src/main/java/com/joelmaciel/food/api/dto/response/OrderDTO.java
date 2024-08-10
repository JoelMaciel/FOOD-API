package com.joelmaciel.food.api.dto.response;

import com.joelmaciel.food.domain.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private BigDecimal subTotal;
    private BigDecimal freightRate;
    private BigDecimal totalValue;
    private OrderStatus status;
    private OffsetDateTime creationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;
    private RestaurantSummaryDTO restaurant;
    private UserDTO client;
    private PaymentMethodDTO paymentMethod;
    private AddressDTO addressDelivery;
    private List<OrderItemDTO> items;


}
