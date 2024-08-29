package com.joelmaciel.food.infra.repository.spec;

import com.joelmaciel.food.domain.model.PhotoProduct;
import com.joelmaciel.food.domain.repository.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public PhotoProduct save(PhotoProduct photoProduct) {
        return manager.merge(photoProduct);
    }

    @Transactional
    @Override
    public void delete(PhotoProduct photoProduct) {
        manager.remove(photoProduct);
    }
}
