package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.CityRequestDTO;
import com.joelmaciel.food.api.dto.response.CityDTO;
import com.joelmaciel.food.domain.model.City;
import com.joelmaciel.food.domain.model.State;

import java.util.List;

public class CityConverter {

    private CityConverter() {
    }

    public static List<CityDTO> toDTOList(List<City> cities) {
        return cities.stream()
                .map(CityConverter::toDTO)
                .toList();
    }

    public static CityDTO toDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .state(city.getState().getName())
                .build();
    }

    public static City toEntity(CityRequestDTO cityRequestDTO, State state) {
        return City.builder()
                .name(cityRequestDTO.getName())
                .state(state)
                .build();
    }

    public static City updateCity(CityRequestDTO cityRequestDTO, City city) {
        return city.toBuilder()
                .name(cityRequestDTO.getName())
                .state(city.getState())
                .build();
    }
}
