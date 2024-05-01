package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kitchens")
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> findAll() {
        return kitchenService.findAll();
    }

    @GetMapping("/{kitchenId}")
    public Kitchen findById(@PathVariable Long kitchenId) {
        return kitchenService.findById(kitchenId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public Kitchen update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        return kitchenService.update(kitchenId, kitchen);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{kitchenId}")
    public void delete(@PathVariable Long kitchenId) {
        kitchenService.remove(kitchenId);
    }
}
