package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.ProductRequestDTO;
import com.joelmaciel.food.api.dto.response.ProductDTO;
import com.joelmaciel.food.domain.model.Product;
import org.springframework.data.domain.Page;

public class ProductConverter {

    private ProductConverter() {
    }

    public static Page<ProductDTO> toPageDTO(Page<Product> productPage) {
        return productPage.map(ProductConverter::toDTO);
    }

    public static Product toEntity(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .active(productRequestDTO.getActive())
                .build();
    }

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .active(product.getActive())
                .build();
    }

    public static Product updateEntity(Product product, ProductRequestDTO productRequestDTO) {
        return product.toBuilder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .active(productRequestDTO.getActive())
                .build();
    }
}
