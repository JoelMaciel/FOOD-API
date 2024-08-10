package com.joelmaciel.food.api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequestDTO {

    private Long productId;
    private Integer quantity;
    private String observation;
}
