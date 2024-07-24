package com.joelmaciel.food.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequestDTO {

    @NotBlank
    private String zipCode;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    private String complement;
    @NotBlank
    private String district;
    @Valid
    @NotNull
    private CityIdRequestDTO city;
}
