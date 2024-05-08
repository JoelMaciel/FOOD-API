package com.joelmaciel.food.api.dto.response;

import com.joelmaciel.food.domain.model.State;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityDTO {

    private Long id;
    private String name;
    private String state;
}
