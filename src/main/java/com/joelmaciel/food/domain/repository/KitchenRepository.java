package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> findAll();
    Kitchen findById(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Kitchen kitchen);
}
