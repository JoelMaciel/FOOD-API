package com.joelmaciel.food.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StateDTO {

    private Long id;
    private String name;
}
