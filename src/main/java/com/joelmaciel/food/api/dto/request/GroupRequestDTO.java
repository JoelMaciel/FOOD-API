package com.joelmaciel.food.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GroupRequestDTO {

    @NotBlank
    private String name;
}
