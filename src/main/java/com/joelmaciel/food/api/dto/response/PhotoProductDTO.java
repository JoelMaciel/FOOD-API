package com.joelmaciel.food.api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoProductDTO {

    private String fileName;
    private String description;
    private String contentType;
    private Long fileSize;
}
