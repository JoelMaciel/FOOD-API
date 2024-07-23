package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.PaymentMethodRequestDTO;
import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.domain.model.PaymentMethod;
import org.springframework.data.domain.Page;

public class PaymentMethodConverter {

    private PaymentMethodConverter() {
    }

    public static PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return PaymentMethodDTO.builder()
                .id(paymentMethod.getId())
                .description(paymentMethod.getDescription())
                .build();
    }

    public static Page<PaymentMethodDTO> toPageDTO(Page<PaymentMethod> paymentMethods) {
        return paymentMethods.map(PaymentMethodConverter::toDTO);
    }


    public static PaymentMethod toEntity(PaymentMethodRequestDTO paymentMethodRequestDTO) {
        return PaymentMethod.builder()
                .description(paymentMethodRequestDTO.getDescription())
                .build();
    }

    public static PaymentMethod toUpdatedEntity(PaymentMethod paymentMethod, PaymentMethodRequestDTO paymentMethodRequestDTO) {
        return paymentMethod.toBuilder()
                .description(paymentMethodRequestDTO.getDescription())
                .build();
    }

}
