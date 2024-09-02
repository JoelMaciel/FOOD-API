package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.model.PhotoProduct;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;
import java.io.InputStream;

public interface PhotoProductService {

    PhotoProduct save(PhotoProduct photoProduct, InputStream inputStream, String fileName);

    PhotoProductDTO savePhotoProduct(Long restaurantId, Long productId, PhotoProductRequest photoProductRequest) throws IOException;

    PhotoProduct optionalPhotoProduct(Long restaurantId, Long productId);

    PhotoProductDTO findPhoto(Long restaurantId, Long productId);

    InputStream retrievePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
}
