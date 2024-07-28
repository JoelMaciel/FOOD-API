package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.response.PermissionDTO;
import com.joelmaciel.food.domain.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public class PermissionConverter {

    private PermissionConverter() {
    }

    public static Page<PermissionDTO> toDTOPage(Set<Permission> permissionList, Pageable pageable) {
        List<PermissionDTO> dtoList = permissionList.stream()
                .map(PermissionConverter::toDTO)
                .toList();

        return new PageImpl<>(dtoList, pageable, permissionList.size());
    }

    public static PermissionDTO toDTO(Permission permission) {
        return PermissionDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
