package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/{userId}/groups")
public class UserGroupController {

    private final UserService userService;

    @GetMapping
    public Page<GroupDTO> getAll(
            @PathVariable Long userId,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.pageUserGroups(userId, pageable);
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.associateGroup(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.disassociateGroup(userId, groupId);
    }
}
