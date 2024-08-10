package com.joelmaciel.food.api.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantIdRequestDTO {

    @NotNull
    private Long id;
}
