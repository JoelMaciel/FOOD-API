package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.domain.model.Permission;

public interface PermissionService {

    Permission optionalPermission(Long permissionId);
}
