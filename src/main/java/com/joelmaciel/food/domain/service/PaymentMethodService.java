package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.request.PaymentMethodRequestDTO;
import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.domain.model.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentMethodService {

    PaymentMethodDTO add(PaymentMethodRequestDTO paymentMethodRequestDTO);

    Page<PaymentMethodDTO> findAll(Pageable pageable);

    PaymentMethodDTO findById(Long paymentMethodId);

    PaymentMethod optionalPaymentMethod(Long paymentMethodId);

    PaymentMethodDTO update(Long paymentMethodId, PaymentMethodRequestDTO paymentMethodRequestDTO);

    void delete(Long paymentMethodId);
}
