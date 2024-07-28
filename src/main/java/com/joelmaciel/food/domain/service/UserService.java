package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.PasswordRequestDTO;
import com.joelmaciel.food.api.dto.request.UserRequestDTO;
import com.joelmaciel.food.api.dto.request.UserWithPasswordRequestDTO;
import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDTO> findAll(Pageable pageable);

    UserDTO findById(Long userId);

    User optionalUser(Long userId);

    UserDTO update(Long userId, UserRequestDTO userRequestDTO);

    UserDTO save(UserWithPasswordRequestDTO userWithPasswordRequestDTO);

    void updatePassword(Long userId, PasswordRequestDTO passwordRequestDTO);
}
