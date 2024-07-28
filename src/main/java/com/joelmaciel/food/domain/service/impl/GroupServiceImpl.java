package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.GroupConverter;
import com.joelmaciel.food.api.dto.converter.PermissionConverter;
import com.joelmaciel.food.api.dto.request.GroupRequestDTO;
import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.api.dto.response.PermissionDTO;
import com.joelmaciel.food.domain.exception.GroupNotFoundException;
import com.joelmaciel.food.domain.model.Group;
import com.joelmaciel.food.domain.model.Permission;
import com.joelmaciel.food.domain.repository.GroupRepository;
import com.joelmaciel.food.domain.service.GroupService;
import com.joelmaciel.food.domain.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final PermissionService permissionService;

    @Override
    public Page<GroupDTO> findAll(Pageable pageable) {
        Page<Group> groupPage = groupRepository.findAll(pageable);
        return GroupConverter.toDTOPage(groupPage);
    }

    @Override
    public GroupDTO findById(Long groupId) {
        Group group = optionalGroup(groupId);
        return GroupConverter.toDTO(group);
    }

    @Transactional
    @Override
    public GroupDTO create(GroupRequestDTO groupRequestDTO) {
        Group group = GroupConverter.toEntity(groupRequestDTO);
        return GroupConverter.toDTO(groupRepository.save(group));
    }

    @Transactional
    @Override
    public GroupDTO update(GroupRequestDTO groupRequestDTO, Long groupId) {
        Group group = optionalGroup(groupId);
        Group groupUpdated = GroupConverter.toUpdateEntity(groupRequestDTO, group);
        return GroupConverter.toDTO(groupRepository.save(groupUpdated));
    }

    @Transactional
    @Override
    public void delete(Long groupId) {
        Group group = optionalGroup(groupId);
        groupRepository.delete(group);
    }

    @Override
    public Page<PermissionDTO> findAllPermissions(Long groupId, Pageable pageable) {
        Group group = optionalGroup(groupId);
        Set<Permission> listPermissions = group.getPermissions();
        return PermissionConverter.toDTOPage(listPermissions, pageable);
    }

    @Transactional
    @Override
    public void associate(Long groupId, Long permissionId) {
        Group group = optionalGroup(groupId);
        Permission permission = permissionService.optionalPermission(permissionId);
        group.addPermission(permission);
    }

    @Transactional
    @Override
    public void disassociate(Long groupId, Long permissionId) {
        Group group = optionalGroup(groupId);
        Permission permission = permissionService.optionalPermission(permissionId);
        group.removePermission(permission);
    }

    @Override
    public Group optionalGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}
