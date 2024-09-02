package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.exception.PhotoProductNotFoundException;
import com.joelmaciel.food.domain.model.PhotoProduct;
import com.joelmaciel.food.domain.model.Product;
import com.joelmaciel.food.domain.repository.ProductRepository;
import com.joelmaciel.food.domain.service.PhotoProductService;
import com.joelmaciel.food.domain.service.PhotoStorageService;
import com.joelmaciel.food.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoProductServiceImpl implements PhotoProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final PhotoStorageService photoStorageService;

    @Transactional
    @Override
    public PhotoProductDTO savePhotoProduct(Long restaurantId, Long productId, PhotoProductRequest photoProductRequest) throws IOException {
        Product product = validatePhotoProduct(restaurantId, productId);

        String newFileName = photoStorageService.generateFileName(photoProductRequest.getFile().getOriginalFilename());

        PhotoProduct photoProduct = toEntity(product, photoProductRequest, newFileName);

        PhotoProduct savedPhotoProduct = save(photoProduct, photoProductRequest.getFile().getInputStream(), newFileName);

        return toDTO(savedPhotoProduct);
    }

    @Override
    public PhotoProduct optionalPhotoProduct(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new PhotoProductNotFoundException(restaurantId, productId));
    }

    @Override
    public PhotoProductDTO findPhoto(Long restaurantId, Long productId) {
        PhotoProduct photoProduct = optionalPhotoProduct(restaurantId, productId);

        return toDTO(photoProduct);
    }

    private Product validatePhotoProduct(Long restaurantId, Long productId) {
        Product product = productService.optionalProductRestaurant(restaurantId, productId);

        Optional<PhotoProduct> existingPhoto = productRepository.findPhotoById(restaurantId, productId);

        existingPhoto.ifPresent(photoProduct -> {
            photoStorageService.remove(photoProduct.getFileName());
            productRepository.delete(photoProduct);
        });
        return product;
    }

    @Transactional
    @Override
    public PhotoProduct save(PhotoProduct photoProduct, InputStream inputStream, String fileName) {
        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService
                .NewPhoto.builder()
                .fileName(fileName)
                .inputStream(inputStream)
                .build();

        photoStorageService.storePhoto(newPhoto);
        PhotoProduct savedPhotoProduct = productRepository.save(photoProduct);
        productRepository.flush();
        return savedPhotoProduct;
    }

    private PhotoProduct toEntity(Product product, PhotoProductRequest photoProductRequest, String fileName) {
        return PhotoProduct.builder()
                .product(product)
                .fileName(fileName)
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
