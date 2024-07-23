package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.PaymentMethodConverter;
import com.joelmaciel.food.api.dto.request.PaymentMethodRequestDTO;
import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.domain.exception.PaymentMethodNotFoundException;
import com.joelmaciel.food.domain.model.PaymentMethod;
import com.joelmaciel.food.domain.repository.PaymentMethodRepository;
import com.joelmaciel.food.domain.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public Page<PaymentMethodDTO> findAll(Pageable pageable) {
        Page<PaymentMethod> paymentMethodPage = paymentMethodRepository.findAll(pageable);
        return PaymentMethodConverter.toPageDTO(paymentMethodPage);
    }

    @Override
    public PaymentMethodDTO findById(Long paymentMethodId) {
        PaymentMethod paymentMethod = optionalPaymentMethod(paymentMethodId);
        return PaymentMethodConverter.toDTO(paymentMethod);
    }

    @Override
    public PaymentMethod optionalPaymentMethod(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }

    @Transactional
    @Override
    public PaymentMethodDTO update(Long paymentMethodId, PaymentMethodRequestDTO paymentMethodRequestDTO) {
        PaymentMethod paymentMethod = optionalPaymentMethod(paymentMethodId);
        PaymentMethod updatedPayment = PaymentMethodConverter.toUpdatedEntity(paymentMethod, paymentMethodRequestDTO);
        return PaymentMethodConverter.toDTO(paymentMethodRepository.save(updatedPayment));
    }

    @Transactional
    @Override
    public PaymentMethodDTO add(PaymentMethodRequestDTO paymentMethodRequestDTO) {
        PaymentMethod paymentMethod = PaymentMethodConverter.toEntity(paymentMethodRequestDTO);
        return PaymentMethodConverter.toDTO(paymentMethodRepository.save(paymentMethod));
    }

    @Transactional
    @Override
    public void delete(Long paymentMethodId) {
        PaymentMethod paymentMethod = optionalPaymentMethod(paymentMethodId);
        paymentMethodRepository.delete(paymentMethod);
    }
}
