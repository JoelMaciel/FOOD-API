package com.joelmaciel.food.infra.service.report;

import com.joelmaciel.food.domain.filter.DailySalesFilter;
import com.joelmaciel.food.domain.service.SalesQueryService;
import com.joelmaciel.food.domain.service.SalesReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class PdfSalesReportServiceImpl implements SalesReportService {

    private final SalesQueryService salesQueryService;

    @Override
    public byte[] issueDailySales(DailySalesFilter filter, String timeOffSet) {
        try {
            var inputStream = this.getClass().getResourceAsStream("/reports/daily-sales.jasper");

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var dailySales = salesQueryService.consultDailySales(filter, timeOffSet);

            var dataSource = new JRBeanCollectionDataSource(dailySales);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Nao foi possivel emitir relatorio de vendas diarias", e);
        }


    }
}
