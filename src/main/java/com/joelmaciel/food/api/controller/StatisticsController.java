package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.DailySalesDTO;
import com.joelmaciel.food.domain.filter.DailySalesFilter;
import com.joelmaciel.food.domain.service.SalesQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final SalesQueryService salesQueryService;

    @GetMapping("/daily-sales")
    public List<DailySalesDTO> consultDailySales(DailySalesFilter dailySalesFilter) {
        return salesQueryService.consultDailySales(dailySalesFilter);
    }
}
