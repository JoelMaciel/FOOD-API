package com.joelmaciel.food.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank
    private String  name;

    @NotBlank
    @Email
    private String email;
}
