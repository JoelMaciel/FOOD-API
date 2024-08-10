package com.joelmaciel.food.api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantSummaryDTO {

    private Long id;
    private String name;
}
