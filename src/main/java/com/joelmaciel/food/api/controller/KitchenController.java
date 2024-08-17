package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kitchens")
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    public Page<KitchenDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return kitchenService.findAll(pageable);
    }

    @GetMapping("/{kitchenId}")
    public KitchenDTO findById(@PathVariable Long kitchenId) {
        return kitchenService.findById(kitchenId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public KitchenDTO add(@RequestBody @Valid KitchenRequestDTO kitchenRequestDTO) {
        return kitchenService.save(kitchenRequestDTO);
    }

    @PutMapping("/{kitchenId}")
    public KitchenDTO update(@PathVariable Long kitchenId, @RequestBody @Valid KitchenRequestDTO kitchenRequestDTO) {
        return kitchenService.update(kitchenId, kitchenRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{kitchenId}")
    public void delete(@PathVariable Long kitchenId) {
        kitchenService.remove(kitchenId);
    }
}
