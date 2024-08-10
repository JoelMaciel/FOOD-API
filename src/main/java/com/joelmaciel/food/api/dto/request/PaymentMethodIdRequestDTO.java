package com.joelmaciel.food.api.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodIdRequestDTO {

    @NotNull
    private Long id;
}
