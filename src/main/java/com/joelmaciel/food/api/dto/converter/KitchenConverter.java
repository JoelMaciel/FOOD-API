package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.model.Kitchen;
import org.springframework.data.domain.Page;

public class KitchenConverter {
    private KitchenConverter() {
    }

    public static Page<KitchenDTO> toPageDTO(Page<Kitchen> kitchens) {
        return kitchens.map(KitchenConverter::toDTO);
    }

    public static KitchenDTO toDTO(Kitchen kitchen) {
        return KitchenDTO.builder()
                .id(kitchen.getId())
                .name(kitchen.getName())
                .build();
    }

    public static Kitchen toEntity(KitchenRequestDTO kitchenRequestDTO) {
        return Kitchen.builder()
                .name(kitchenRequestDTO.getName())
                .build();
    }
}
