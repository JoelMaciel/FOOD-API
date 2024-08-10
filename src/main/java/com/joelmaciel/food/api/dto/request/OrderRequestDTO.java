package com.joelmaciel.food.api.dto.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @Valid
    @NotNull
    private RestaurantIdRequestDTO restaurant;

    @Valid
    @NotNull
    private AddressRequestDTO addressDelivery;

    @Valid
    @NotNull
    private PaymentMethodIdRequestDTO paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemRequestDTO> items;
}
