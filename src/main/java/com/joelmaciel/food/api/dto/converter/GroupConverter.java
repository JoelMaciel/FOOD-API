package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.GroupRequestDTO;
import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.domain.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public class GroupConverter {

    private GroupConverter() {
    }

    public static Page<GroupDTO> toDTOPage(Set<Group> groups, Pageable pageable) {
        List<Group> groupList = groups.stream().toList();
        List<GroupDTO> groupDTOList = groupList.stream()
                .map(GroupConverter::toDTO)
                .toList();

        int start = Math.min((int) pageable.getOffset(), groupDTOList.size());
        int end = Math.min((start + pageable.getPageSize()), groupDTOList.size());

        return new PageImpl<>(groupDTOList.subList(start, end), pageable, groupDTOList.size());
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
