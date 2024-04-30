package com.joelmaciel.food.infrastructure.repository;

import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurant> findAll() {
        return entityManager.createQuery("from Restaurant", Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant findById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Override
    @Transactional
    public void remove(Restaurant restaurant) {
        Restaurant kitchen1 = findById(restaurant.getId());
        entityManager.remove(kitchen1);
    }
}
