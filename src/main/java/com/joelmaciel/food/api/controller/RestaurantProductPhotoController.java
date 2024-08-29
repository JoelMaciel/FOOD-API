package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.PhotoProductRequest;
import com.joelmaciel.food.api.dto.response.PhotoProductDTO;
import com.joelmaciel.food.domain.service.PhotoProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    private final PhotoProductService photoProductService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductDTO updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                       @Valid PhotoProductRequest photoProductRequest) {
       return photoProductService.savePhotoProduct(restaurantId, productId, photoProductRequest);
    }
}
