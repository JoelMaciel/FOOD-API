package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.UserDTO;
import com.joelmaciel.food.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/{restaurantId}/responsible")
public class RestaurantUserResponsibleController {

    private final RestaurantService restaurantService;

    @GetMapping
    public Page<UserDTO> getAll(
            @PathVariable Long restaurantId,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return restaurantService.findAllResponsible(restaurantId, pageable);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateUser(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId , @PathVariable Long userId) {
        restaurantService.disassociateUser(restaurantId, userId);
    }

}
