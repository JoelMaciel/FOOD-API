package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.model.PhotoProduct;
import com.joelmaciel.food.domain.model.Product;
import com.joelmaciel.food.domain.repository.ProductRepository;
import com.joelmaciel.food.domain.service.PhotoProductService;
import com.joelmaciel.food.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoProductServiceImpl implements PhotoProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Transactional
    @Override
    public PhotoProductDTO savePhotoProduct(Long restaurantId, Long productId, PhotoProductRequest photoProductRequest) {
        Product product = validadePhotoProduct(restaurantId, productId);
        PhotoProduct photoProduct = save(toEntity(product, photoProductRequest));
        return toDTO(photoProduct);
    }

    private Product validadePhotoProduct(Long restaurantId, Long productId) {
        Product product = productService.optionalProductRestaurant(restaurantId, productId);

        Optional<PhotoProduct> existingPhoto = productRepository.findPhotoById(restaurantId, productId);

        existingPhoto.ifPresent(productRepository::delete);
        return product;
    }

    @Transactional
    @Override
    public PhotoProduct save(PhotoProduct photoProduct) {
        return productRepository.save(photoProduct);
    }

    private PhotoProduct toEntity(Product product, PhotoProductRequest photoProductRequest) {
        return PhotoProduct.builder()
                .product(product)
                .fileName(photoProductRequest.getFile().getOriginalFilename())
                .description(photoProductRequest.getDescription())
                .contentType(photoProductRequest.getFile().getContentType())
                .fileSize(photoProductRequest.getFile().getSize())
                .build();
    }

    private PhotoProductDTO toDTO(PhotoProduct photoProduct) {
        return PhotoProductDTO.builder()
                .fileName(photoProduct.getFileName())
                .description(photoProduct.getDescription())
                .contentType(photoProduct.getContentType())
                .fileSize(photoProduct.getFileSize())
                .build();
    }
}
