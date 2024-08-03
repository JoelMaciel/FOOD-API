package com.joelmaciel.food.api.dto.response;

import com.joelmaciel.food.domain.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummaryDTO {

    private Long id;
    private BigDecimal subTotal;
    private BigDecimal freightRate;
    private BigDecimal totalValue;
    private OrderStatus status;
    private OffsetDateTime creationDate;
    private RestaurantSummaryDTO restaurant;
    private UserDTO client;
}
