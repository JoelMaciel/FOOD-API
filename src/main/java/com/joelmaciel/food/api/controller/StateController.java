package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.StateRequestDTO;
import com.joelmaciel.food.api.dto.response.StateDTO;
import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/states")
public class StateController {

    private final StateService stateService;

    @GetMapping
    public List<StateDTO> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{stateId}")
    public StateDTO findById(@PathVariable Long stateId) {
        return stateService.findById(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateDTO add(@RequestBody @Valid StateRequestDTO stateRequest) {
        return stateService.save(stateRequest);
    }

    @PutMapping("/{stateId}")
    public StateDTO update(@PathVariable Long stateId, @RequestBody @Valid StateRequestDTO stateRequest) {
        return stateService.update(stateId, stateRequest);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        stateService.remove(stateId);
    }
}
