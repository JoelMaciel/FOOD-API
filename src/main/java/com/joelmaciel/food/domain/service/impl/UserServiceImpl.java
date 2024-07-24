package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.UserConverter;
import com.joelmaciel.food.api.dto.request.PasswordRequestDTO;
import com.joelmaciel.food.api.dto.request.UserRequestDTO;
import com.joelmaciel.food.api.dto.request.UserWithPasswordRequestDTO;
import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.EmailAlreadyExistingException;
import com.joelmaciel.food.domain.exception.UserNotFoundException;
import com.joelmaciel.food.domain.model.User;
import com.joelmaciel.food.domain.repository.UserRepository;
import com.joelmaciel.food.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return UserConverter.toPageDTO(userPage);
    }

    @Override
    public UserDTO save(UserWithPasswordRequestDTO userWithPasswordRequestDTO) {
        validateEmail(userWithPasswordRequestDTO.getEmail());

        User user = UserConverter.toEntity(userWithPasswordRequestDTO);
        return UserConverter.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO findById(Long userId) {
        User user = optionalUser(userId);
        return UserConverter.toDTO(user);
    }

    @Transactional
    @Override
    public UserDTO update(Long userId, UserRequestDTO userRequestDTO) {
        User user = optionalUser(userId);

        validateEmail(userRequestDTO.getEmail());

        User userUpdated = UserConverter.toEntityUpdated(user, userRequestDTO);
        return UserConverter.toDTO(userRepository.save(userUpdated));
    }

    @Transactional
    @Override
    public void updatePassword(Long userId, PasswordRequestDTO passwordRequestDTO) {
        User user = optionalUser(userId);
        if (user.passwordDoesNotMatch(passwordRequestDTO.getCurrentPassword())) {
            throw new BusinessException("Current password does not match the user's password");
        }
        user.setPassword(passwordRequestDTO.getNewPassword());
    }

    @Override
    public User optionalUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private boolean validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistingException(email);
        }
        return false;
    }
}
