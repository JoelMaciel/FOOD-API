package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.GroupConverter;
import com.joelmaciel.food.api.dto.request.GroupRequestDTO;
import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.domain.exception.GroupNotFoundException;
import com.joelmaciel.food.domain.model.Group;
import com.joelmaciel.food.domain.repository.GroupRepository;
import com.joelmaciel.food.domain.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

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
    public Group optionalGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}
