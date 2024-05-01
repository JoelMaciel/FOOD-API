package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.domain.model.City;
import com.joelmaciel.food.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{cityId}")
    public City findById(@PathVariable Long cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City cityRequest) {
        return cityService.save(cityRequest);
    }

    @PutMapping("/{cityId}")
    public City update(@PathVariable Long cityId, @RequestBody City cityRequest) {
        return cityService.update(cityId, cityRequest);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityService.remove(cityId);
    }
}
