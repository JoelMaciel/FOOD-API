package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.GroupRequestDTO;
import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.domain.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Page<GroupDTO> findAll(Pageable pageable);

    GroupDTO findById(Long groupId);

    Group optionalGroup(Long groupId);

    GroupDTO create(GroupRequestDTO groupRequestDTO);

    GroupDTO update(GroupRequestDTO groupRequestDTO, Long groupId);

    void delete(Long groupId);
}
