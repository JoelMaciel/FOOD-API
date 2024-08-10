package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.request.OrderRequestDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.service.OrderService;
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
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<OrderSummaryDTO> gelAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return orderService.findAll(pageable);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOne(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO add(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        return orderService.addOrder(orderRequestDTO);
    }
}
