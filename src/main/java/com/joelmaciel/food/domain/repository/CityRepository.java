package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> findAll();

    City findById(Long id);

    City save(City city);

    void remove(City city);
}
