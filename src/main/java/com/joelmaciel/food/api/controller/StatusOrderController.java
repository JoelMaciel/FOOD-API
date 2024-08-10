package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.domain.service.StatusOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders/{orderId}")
public class StatusOrderController {

    private final StatusOrderService statusOrderService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmOrder(@PathVariable Long orderId) {
        statusOrderService.confirm(orderId);
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable Long orderId) {
        statusOrderService.cancel(orderId);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliveryOrder(@PathVariable Long orderId) {
        statusOrderService.deliver(orderId);
    }
}
