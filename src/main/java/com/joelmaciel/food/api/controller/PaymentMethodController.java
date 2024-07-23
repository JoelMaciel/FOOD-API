package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.request.PaymentMethodRequestDTO;
import com.joelmaciel.food.api.dto.response.PaymentMethodDTO;
import com.joelmaciel.food.domain.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public Page<PaymentMethodDTO> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return paymentMethodService.findAll(pageable);
    }

    @GetMapping("/{paymentMethodId}")
    public PaymentMethodDTO getOne(@PathVariable Long paymentMethodId) {
        return paymentMethodService.findById(paymentMethodId);
    }

    @PutMapping("/{paymentMethodId}")
    public PaymentMethodDTO update(
            @PathVariable Long paymentMethodId,
            @RequestBody @Valid PaymentMethodRequestDTO paymentMethodRequestDTO) {
        return paymentMethodService.update(paymentMethodId, paymentMethodRequestDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodDTO toAdd(@RequestBody @Valid PaymentMethodRequestDTO paymentMethodRequestDTO) {
        return paymentMethodService.add(paymentMethodRequestDTO);
    }

    @DeleteMapping("/{paymentMethodId}")
    public void delete(@PathVariable Long paymentMethodId) {
        paymentMethodService.delete(paymentMethodId);
    }

}
