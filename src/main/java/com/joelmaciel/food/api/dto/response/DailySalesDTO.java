package com.joelmaciel.food.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class DailySalesDTO {

    private Date date;
    private Long totalSales;
    private BigDecimal totalInvoiced;
}
