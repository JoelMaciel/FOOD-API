package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/states")
public class StateController {

    private final StateService stateService;

    @GetMapping
    public List<State> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{stateId}")
    public State findById(@PathVariable Long stateId) {
        return stateService.findById(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State stateRequest) {
        return stateService.save(stateRequest);
    }

    @PutMapping("/{stateId}")
    public State update(@PathVariable Long stateId, @RequestBody State stateRequest) {
        return stateService.update(stateId, stateRequest);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        stateService.remove(stateId);
    }
}
