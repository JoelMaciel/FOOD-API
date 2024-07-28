package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.PasswordRequestDTO;
import com.joelmaciel.food.api.dto.request.UserRequestDTO;
import com.joelmaciel.food.api.dto.request.UserWithPasswordRequestDTO;
import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserDTO> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{userId}")
    public UserDTO getOne(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    public UserDTO update(@PathVariable Long userId, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.update(userId, userRequestDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserWithPasswordRequestDTO userWithPasswordRequestDTO) {
        return userService.save(userWithPasswordRequestDTO);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId, @RequestBody @Valid PasswordRequestDTO passwordRequestDTO) {
        userService.updatePassword(userId, passwordRequestDTO);
    }

}
