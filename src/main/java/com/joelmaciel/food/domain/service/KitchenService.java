package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.domain.model.Kitchen;

import java.util.List;

public interface KitchenService {

    List<Kitchen> findAll();

    Kitchen findById(Long kitchenId);

    Kitchen save(Kitchen kitchen);

    Kitchen update(Long kitchenId, Kitchen kitchen);

    void remove(Long kitchenId);
}
