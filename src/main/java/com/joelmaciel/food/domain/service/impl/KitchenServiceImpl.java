package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import com.joelmaciel.food.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {

    public static final String KITCHEN_IN_USE = "Kitchen cannot be excluded as it is in use";
    private final KitchenRepository kitchenRepository;

    @Override
    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    @Override
    public Kitchen findById(Long kitchenId) {
        return kitchenById(kitchenId);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    @Transactional
    public Kitchen update(Long kitchenId, Kitchen kitchen) {
        Kitchen kitchenActual = kitchenById(kitchenId);
        kitchenActual.setName(kitchen.getName());
        return save(kitchenActual);
    }

    @Override
    @Transactional
    public void remove(Long kitchenId) {
        kitchenById(kitchenId);
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(KITCHEN_IN_USE);
        }
    }

    public Kitchen kitchenById(Long kitchenId) {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }
}
