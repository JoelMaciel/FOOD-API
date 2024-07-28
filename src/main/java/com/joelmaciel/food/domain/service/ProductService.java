package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.ProductRequestDTO;
import com.joelmaciel.food.api.dto.response.ProductDTO;
import com.joelmaciel.food.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDTO> findAll(Long restaurantId, Pageable pageable);

    Product optionalProductRestaurant(Long restaurantId, Long productId);

    ProductDTO findProductRestaurant(Long restaurantId, Long productId);

    ProductDTO saveProductRestaurant(Long restaurantId, ProductRequestDTO productRequestDTO);

    ProductDTO updateProductRestaurant(Long restaurantId, Long productId, ProductRequestDTO productRequestDTO);
}
