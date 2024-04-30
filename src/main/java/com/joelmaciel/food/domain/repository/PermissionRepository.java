package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> findAll();

    Permission findById(Long id);

    Permission save(Permission permission);

    void remove(Permission permission);
}
