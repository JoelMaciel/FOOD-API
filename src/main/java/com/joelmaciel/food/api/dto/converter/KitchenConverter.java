package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.model.Kitchen;

import java.util.List;

public class KitchenConverter {
    private KitchenConverter() {
    }

    public static List<KitchenDTO> toDTOList(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(KitchenConverter::toDTO)
                .toList();
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
