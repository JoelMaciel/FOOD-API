package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
