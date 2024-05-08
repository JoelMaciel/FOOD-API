package com.joelmaciel.food.api.dto.request;

import com.joelmaciel.food.domain.model.Kitchen;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantRequestDTO {

    @NotBlank
    private String name;
    @NotNull
    @PositiveOrZero
    private BigDecimal freightRate;
    @NotNull
    private Long kitchenId;


}
