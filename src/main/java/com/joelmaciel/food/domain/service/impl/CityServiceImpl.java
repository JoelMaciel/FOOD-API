package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.CityNotFoundException;
import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.model.City;
import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.repository.CityRepository;
import com.joelmaciel.food.domain.service.CityService;
import com.joelmaciel.food.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    public static final String CITY_IN_USE = "City cannot be excluded as it is in use";
    public static final String STATE_NOT_FOUND = "ID state %d not found";

    private final CityRepository cityRepository;
    private final StateService stateService;


    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long cityId) {
        return cityById(cityId);
    }

    @Override
    @Transactional
    public City save(City cityRequest) {
        try {
            return cityRepository.save(cityRequest);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(
                    String.format(STATE_NOT_FOUND, cityRequest.getState().getId()));
        }
    }

    @Override
    @Transactional
    public City update(Long cityId, City cityRequest) {
        City city = cityById(cityId);
        State state = stateService.findById(cityRequest.getState().getId());

        city.setName(cityRequest.getName());
        city.setState(state);

        return save(city);
    }

    @Override
    @Transactional
    public void remove(Long cityId) {
        cityById(cityId);
        try {
            cityRepository.deleteById(cityId);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(CITY_IN_USE);
        }
    }

    public City cityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
    }
}
