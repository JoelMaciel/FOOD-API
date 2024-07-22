package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.CityRequestDTO;
import com.joelmaciel.food.api.dto.response.CityDTO;
import com.joelmaciel.food.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityDTO> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{cityId}")
    public CityDTO findById(@PathVariable Long cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO add(@RequestBody @Valid CityRequestDTO cityRequest) {
        return cityService.save(cityRequest);
    }

    @PutMapping("/{cityId}")
    public CityDTO update(@PathVariable Long cityId, @RequestBody @Valid CityRequestDTO cityRequest) {
        return cityService.update(cityId, cityRequest);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityService.remove(cityId);
    }
}
