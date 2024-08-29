package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.PhotoProduct;

public interface ProductRepositoryQueries {

    PhotoProduct save(PhotoProduct photoProduct);

    void delete(PhotoProduct photoProduct);
}
