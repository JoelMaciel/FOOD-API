package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.KitchenConverter;
import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import com.joelmaciel.food.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {

    public static final String MSG_KITCHEN_IN_USE = "Kitchen cannot be excluded as it is in use";
    private static final String MSG_KITCHEN_NOT_FOUND = "There is no state with this code %d";
    private final KitchenRepository kitchenRepository;

    @Override
    public List<KitchenDTO> findAll() {
        return KitchenConverter.toDTOList(kitchenRepository.findAll());
    }

    @Override
    public KitchenDTO findById(Long kitchenId) {
        return KitchenConverter.toDTO(optionalKitchen(kitchenId));
    }

    @Override
    @Transactional
    public KitchenDTO save(KitchenRequestDTO kitchenRequestDTO) {
        Kitchen kitchen = KitchenConverter.toEntity(kitchenRequestDTO);
        return KitchenConverter.toDTO(kitchenRepository.save(kitchen));
    }

    @Override
    @Transactional
    public KitchenDTO update(Long kitchenId, KitchenRequestDTO kitchenRequestDTO) {
        Kitchen kitchenActual = optionalKitchen(kitchenId);
        kitchenActual.setName(kitchenRequestDTO.getName());
        return KitchenConverter.toDTO(kitchenRepository.save(kitchenActual));
    }

    @Override
    @Transactional
    public void remove(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(MSG_KITCHEN_IN_USE);
        } catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, kitchenId));
        }
    }

    @Override
    public Kitchen optionalKitchen(Long kitchenId) {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }

}
