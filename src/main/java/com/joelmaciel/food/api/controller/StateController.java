package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/states")
public class StateController {

    private final StateService stateService;

    @GetMapping
    public List<State> findAll() {
        return stateService.findAll();
    }
}
