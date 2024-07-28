package com.joelmaciel.food.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PasswordRequestDTO {

    @NotBlank
    private String currentPassword;

    @Size(min = 8, max = 50)
    @NotBlank
    private String newPassword;
}
