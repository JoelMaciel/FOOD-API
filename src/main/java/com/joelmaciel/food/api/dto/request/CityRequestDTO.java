package com.joelmaciel.food.api.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CityRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private Long stateId;
}
