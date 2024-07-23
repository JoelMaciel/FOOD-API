package com.joelmaciel.food.api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String district;
    private CityDTO city;
}
