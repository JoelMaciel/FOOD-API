package com.joelmaciel.food.api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDTO {

    private Long id;
    private String name;
    private String description;
}
