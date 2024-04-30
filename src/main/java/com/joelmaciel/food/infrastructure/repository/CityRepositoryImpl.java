package com.joelmaciel.food.infrastructure.repository;

import com.joelmaciel.food.domain.model.City;
import com.joelmaciel.food.domain.repository.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> findAll() {
        return entityManager.createQuery("from City", City.class)
                .getResultList();
    }

    @Override
    public City findById(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void remove(City city) {
        City cityTwo = findById(city.getId());
        entityManager.remove(cityTwo);
    }
}
