package com.joelmaciel.food.infrastructure.repository;

import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Kitchen> findAll() {
        return entityManager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public Kitchen findById(Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(Kitchen kitchen) {
        Kitchen kitchen1 = findById(kitchen.getId());
        entityManager.remove(kitchen1);
    }
}
