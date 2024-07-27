package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.ProductRequestDTO;
import com.joelmaciel.food.api.dto.response.ProductDTO;
import com.joelmaciel.food.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getAllProductsRestaurant(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long restaurantId) {
        return productService.findAll(restaurantId, pageable);
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        return productService.findProductRestaurant(restaurantId, productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO add(
            @PathVariable Long restaurantId,
            @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return productService.saveProductRestaurant(restaurantId, productRequestDTO);
    }

    @PutMapping("/{productId}")
    public ProductDTO update(
            @PathVariable Long restaurantId, @PathVariable Long productId,
            @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return productService.updateProductRestaurant(restaurantId, productId, productRequestDTO);
    }
}
