package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.UserRequestDTO;
import com.joelmaciel.food.api.dto.request.UserWithPasswordRequestDTO;
import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.model.User;
import org.springframework.data.domain.Page;

public class UserConverter {

    private UserConverter() {
    }

    public static Page<UserDTO> toPageDTO(Page<User> userPages) {
        return userPages.map(UserConverter::toDTO);
    }

    public static User toEntity(UserWithPasswordRequestDTO userWithPasswordRequestDTO) {
        return User.builder()
                .name(userWithPasswordRequestDTO.getName())
                .email(userWithPasswordRequestDTO.getEmail())
                .password(userWithPasswordRequestDTO.getPassword())
                .build();
    }

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .registrationDate(user.getRegistrationDate())
                .build();
    }

    public static User toEntityUpdated(User user, UserRequestDTO userRequestDTO) {
        return user.toBuilder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .build();
    }
}
