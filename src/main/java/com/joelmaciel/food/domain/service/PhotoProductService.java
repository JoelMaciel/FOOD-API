package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.model.PhotoProduct;

public interface PhotoProductService {

    PhotoProduct save(PhotoProduct product);

    PhotoProductDTO savePhotoProduct(Long restaurantId, Long productId, PhotoProductRequest photoProductRequest);
}
