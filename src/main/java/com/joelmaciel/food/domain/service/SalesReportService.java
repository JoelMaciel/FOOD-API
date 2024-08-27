package com.joelmaciel.food.domain.service;

import com.joelmaciel.food.domain.filter.DailySalesFilter;

public interface SalesReportService {

    byte[] issueDailySales(DailySalesFilter filter, String timeOffSet);
}
