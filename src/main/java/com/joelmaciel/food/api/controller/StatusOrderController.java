package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.domain.service.StatusOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders/{codeOrder}")
public class StatusOrderController {

    private final StatusOrderService statusOrderService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmOrder(@PathVariable String codeOrder) {
        statusOrderService.confirm(codeOrder);
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable String codeOrder) {
        statusOrderService.cancel(codeOrder);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliveryOrder(@PathVariable String codeOrder) {
        statusOrderService.deliver(codeOrder);
    }
}
