package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.exception.PermissionNotFoundException;
import com.joelmaciel.food.domain.model.Permission;
import com.joelmaciel.food.domain.repository.PermissionRepository;
import com.joelmaciel.food.domain.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission optionalPermission(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }
}
