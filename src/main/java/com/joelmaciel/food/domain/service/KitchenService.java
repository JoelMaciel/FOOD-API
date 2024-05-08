package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.model.Kitchen;

import java.util.List;

public interface KitchenService {

    List<KitchenDTO> findAll();

    KitchenDTO findById(Long kitchenId);

    KitchenDTO save(KitchenRequestDTO kitchenRequestDTO);

    KitchenDTO update(Long kitchenId, KitchenRequestDTO kitchenRequestDTO);

    void remove(Long kitchenId);
    Kitchen optionalKitchen(Long kitchenId);
}
