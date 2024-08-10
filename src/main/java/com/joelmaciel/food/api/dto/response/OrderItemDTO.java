package com.joelmaciel.food.api.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer quantity;
    private String observation;
}
