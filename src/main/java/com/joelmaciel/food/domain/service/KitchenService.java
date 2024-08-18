package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.model.Kitchen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KitchenService {

    Page<KitchenDTO> findAll(Pageable pageable);

    KitchenDTO findById(Long kitchenId);

    KitchenDTO save(KitchenRequestDTO kitchenRequestDTO);

    KitchenDTO update(Long kitchenId, KitchenRequestDTO kitchenRequestDTO);

    void remove(Long kitchenId);
    Kitchen optionalKitchen(Long kitchenId);
}
