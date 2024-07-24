package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.GroupRequestDTO;
import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.domain.model.Group;
import org.springframework.data.domain.Page;

public class GroupConverter {

    private GroupConverter() {
    }

    public static Page<GroupDTO> toDTOPage(Page<Group> groups) {
        return groups.map(GroupConverter::toDTO);
    }

    public static GroupDTO toDTO(Group group) {
        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }

    public static Group toEntity(GroupRequestDTO groupRequestDTO) {
        return Group.builder()
                .name(groupRequestDTO.getName())
                .build();
    }

    public static Group toUpdateEntity(GroupRequestDTO groupRequestDTO, Group group) {
        return group.toBuilder()
                .name(groupRequestDTO.getName())
                .build();
    }
}
