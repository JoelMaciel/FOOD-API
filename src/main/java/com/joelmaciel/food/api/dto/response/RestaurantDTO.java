package com.joelmaciel.food.api.dto.response;

import com.joelmaciel.food.domain.model.Kitchen;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RestaurantDTO {

    private Long id;
    private String name;
    private BigDecimal freightRate;
    private Boolean active;
    private Boolean open;
    private Kitchen kitchen;
    private AddressDTO address;
}
