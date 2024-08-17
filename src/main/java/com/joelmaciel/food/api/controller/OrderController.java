package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.request.OrderRequestDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.repository.filter.OrderFilter;
import com.joelmaciel.food.domain.service.OrderService;
import com.joelmaciel.food.infra.repository.spec.OrderSpecs;
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
    public Page<OrderSummaryDTO> gelAllProducts(OrderFilter orderFilter,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return orderService.findAll(OrderSpecs.usingFilter(orderFilter),pageable);
    }

    @GetMapping("/{codeOrder}")
    public OrderDTO getOne(@PathVariable String codeOrder) {
        return orderService.findById(codeOrder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO add(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        return orderService.addOrder(orderRequestDTO);
    }
}
