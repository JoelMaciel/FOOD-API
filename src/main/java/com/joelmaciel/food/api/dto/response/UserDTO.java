package com.joelmaciel.food.api.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime registrationDate;
}
