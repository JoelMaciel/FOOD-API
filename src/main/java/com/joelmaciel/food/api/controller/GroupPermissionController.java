package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.PermissionDTO;
import com.joelmaciel.food.domain.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/{groupId}/permissions")
public class GroupPermissionController {

    private final GroupService groupService;

    @GetMapping
    public Page<PermissionDTO> getAll(
            @PathVariable Long groupId,
            @PageableDefault(page = 10, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return groupService.findAllPermissions(groupId, pageable);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.associate(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate (@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.disassociate(groupId, permissionId);
    }

}
