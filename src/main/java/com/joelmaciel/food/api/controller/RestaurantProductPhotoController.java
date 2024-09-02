package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.exception.EntityNotFoundException;
import com.joelmaciel.food.domain.service.PhotoProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final PhotoProductService photoProductService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductDTO updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                       @Valid PhotoProductRequest photoProductRequest) throws IOException {
        return photoProductService.savePhotoProduct(restaurantId, productId, photoProductRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoProductDTO findPhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        return photoProductService.findPhoto(restaurantId, productId);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> retrievePhoto(
            @PathVariable Long restaurantId,
            @PathVariable Long productId,
            @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            InputStream inputStream = photoProductService.retrievePhoto(restaurantId, productId,acceptHeader);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
