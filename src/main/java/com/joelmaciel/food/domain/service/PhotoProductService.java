package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.model.PhotoProduct;

import java.io.IOException;
import java.io.InputStream;

public interface PhotoProductService {

    PhotoProduct save(PhotoProduct photoProduct, InputStream inputStream, String fileName);

    PhotoProductDTO savePhotoProduct(Long restaurantId, Long productId, PhotoProductRequest photoProductRequest) throws IOException;
}
