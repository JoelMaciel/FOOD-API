package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDTO findById(@PathVariable Long restaurantId) {
        return restaurantService.findById(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO add(@RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO) {
        return restaurantService.save(restaurantRequestDTO);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantDTO update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantRequestDTO restaurantRequestDTO) {
        return restaurantService.update(restaurantId, restaurantRequestDTO);
    }

}
