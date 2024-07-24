package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.CityConverter;
import com.joelmaciel.food.api.dto.request.CityRequestDTO;
import com.joelmaciel.food.api.dto.response.CityDTO;
import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.CityNotFoundException;
import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.exception.StateNotFoundException;
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

    private final StateService stateService;

    public static final String CITY_IN_USE = "City cannot be excluded as it is in use";
    public static final String STATE_NOT_FOUND = "ID state %d not found";
    public static final String MSG_STATE_NOT_FOUND = "There is no state registered with this id";

    private final CityRepository cityRepository;

    @Override
    public List<CityDTO> findAll() {
        return CityConverter.toDTOList(cityRepository.findAll());
    }

    @Override
    public CityDTO findById(Long cityId) {
        return CityConverter.toDTO(optionalCity(cityId));
    }

    public CityDTO save(CityRequestDTO cityRequest) {
        try {
            State state = stateService.optionalState(cityRequest.getStateId());
            City city = CityConverter.toEntity(cityRequest, state);
            return CityConverter.toDTO(cityRepository.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(
                    String.format(STATE_NOT_FOUND, cityRequest.getStateId()));
        }
    }

    @Override
    @Transactional
    public CityDTO update(Long cityId, CityRequestDTO cityRequest) {
        try {
            City city = optionalCity(cityId);
            city = CityConverter.updateCity(cityRequest, city);
            State state = stateService.optionalState(cityRequest.getStateId());
            city.setState(state);

            return CityConverter.toDTO(cityRepository.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(MSG_STATE_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void remove(Long cityId) {
        optionalCity(cityId);
        try {
            cityRepository.deleteById(cityId);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(CITY_IN_USE);
        }
    }

    @Override
    public City optionalCity(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
    }
}
