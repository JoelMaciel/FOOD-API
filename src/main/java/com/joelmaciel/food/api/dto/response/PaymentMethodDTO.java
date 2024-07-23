package com.joelmaciel.food.api.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodDTO {

    private Long id;
    private String description;
}
