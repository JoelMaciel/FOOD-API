package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.GroupRequestDTO;
import com.joelmaciel.food.api.dto.response.GroupDTO;
import com.joelmaciel.food.domain.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public Page<GroupDTO> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return groupService.findAll(pageable);
    }

    @GetMapping("/{groupId}")
    public GroupDTO getOne(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO create(@RequestBody @Valid GroupRequestDTO groupRequestDTO) {
        return groupService.create(groupRequestDTO);
    }

    @PutMapping("/{groupId}")
    public GroupDTO update(@RequestBody @Valid GroupRequestDTO groupRequestDTO, @PathVariable Long groupId) {
        return groupService.update(groupRequestDTO,groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
    }
}
