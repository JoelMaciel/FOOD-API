package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.ProductConverter;
import com.joelmaciel.food.api.dto.request.ProductRequestDTO;
import com.joelmaciel.food.api.dto.response.ProductDTO;
import com.joelmaciel.food.domain.exception.ProductNotFoundException;
import com.joelmaciel.food.domain.model.Product;
import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.repository.ProductRepository;
import com.joelmaciel.food.domain.service.ProductService;
import com.joelmaciel.food.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RestaurantService restaurantService;

    @Override
    public Page<ProductDTO> findAll(Long restaurantId, Pageable pageable, boolean includeInactive) {
        Page<Product> productPage = null;
        Restaurant restaurant = restaurantService.optinalRestaurant(restaurantId);

        productPage = getProducts(pageable, includeInactive, restaurant);

        return ProductConverter.toPageDTO(productPage);
    }

    private Page<Product> getProducts(Pageable pageable, boolean includeInactive, Restaurant restaurant) {
        return includeInactive
                ? productRepository.findByRestaurant(restaurant, pageable)
                : productRepository.findActiveByRestaurant(restaurant, pageable);
    }

    @Override
    public ProductDTO findProductRestaurant(Long restaurantId, Long productId) {
        return ProductConverter.toDTO(optionalProductRestaurant(restaurantId, productId));
    }

    @Transactional
    @Override
    public ProductDTO saveProductRestaurant(Long restaurantId, ProductRequestDTO productRequestDTO) {
        Restaurant restaurant = restaurantService.optinalRestaurant(restaurantId);
        Product product = ProductConverter.toEntity(productRequestDTO);
        product.setRestaurant(restaurant);
        return ProductConverter.toDTO(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductDTO updateProductRestaurant(Long restaurantId, Long productId, ProductRequestDTO productRequestDTO) {
        Product product = optionalProductRestaurant(restaurantId, productId);
        Product productUpdated = ProductConverter.updateEntity(product, productRequestDTO);
        return ProductConverter.toDTO(productUpdated);
    }

    @Override
    public Product optionalProductRestaurant(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new ProductNotFoundException(productId, restaurantId));
    }
}
