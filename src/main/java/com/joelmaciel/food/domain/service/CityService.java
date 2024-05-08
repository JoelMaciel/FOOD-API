package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.CityRequestDTO;
import com.joelmaciel.food.api.dto.response.CityDTO;
import com.joelmaciel.food.domain.model.City;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll();

    CityDTO findById(Long cityId);

    CityDTO save(CityRequestDTO cityRequest);

    CityDTO update(Long cityId, CityRequestDTO cityRequest);

    void remove(Long cityId);
}
