package com.joelmaciel.food.api.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodRequestDTO {

    @NotBlank
    private String description;
}
