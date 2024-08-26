package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.api.dto.response.DailySalesDTO;
import com.joelmaciel.food.domain.filter.DailySalesFilter;

import java.util.List;

public interface SalesQueryService {

    List<DailySalesDTO> consultDailySales(DailySalesFilter dailySalesFilter, String timeOffset);

}
