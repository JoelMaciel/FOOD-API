package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.domain.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findById(Long cityId);

    City save(City cityRequest);

    City update(Long cityId, City cityRequest);

    void remove(Long cityId);
}
