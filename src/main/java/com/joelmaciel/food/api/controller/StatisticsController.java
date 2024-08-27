package com.joelmaciel.food.api.controller;

import com.joelmaciel.food.api.dto.response.DailySalesDTO;
import com.joelmaciel.food.domain.filter.DailySalesFilter;
import com.joelmaciel.food.domain.service.SalesQueryService;
import com.joelmaciel.food.domain.service.SalesReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final SalesQueryService salesQueryService;
    private final SalesReportService salesReportService;

    @GetMapping(value = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySalesDTO> consultDailySales(
            DailySalesFilter dailySalesFilter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return salesQueryService.consultDailySales(dailySalesFilter, timeOffset);
    }

    @GetMapping(value = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultDailySalesPdf(
            DailySalesFilter dailySalesFilter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) throws JRException {
        byte[] bytesPdf = salesReportService.issueDailySales(dailySalesFilter, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }
}
