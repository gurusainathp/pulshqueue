package com.gurusainathp.pulsequeue.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.gurusainathp.pulsequeue.service.SalesReportService;

@Component
public class SalesReportHandler implements JobHandler {
    private final SalesReportService salesReportService;

    public SalesReportHandler(SalesReportService salesReportService) {
        this.salesReportService = salesReportService;
    }

    @Override
    public String getType() {
        return "SALES_REPORT";
    }

    @Override
    public void validate(Map<String, String> parameters) throws IllegalArgumentException {
        String startDateStr = parameters.get("startDate");
        String endDateStr = parameters.get("endDate");

        if (startDateStr == null || startDateStr.isBlank()
                || endDateStr == null || endDateStr.isBlank()) {
            throw new IllegalArgumentException("Missing required parameters: startDate and endDate");
        }

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }

        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("startDate must be before endDate.");
        }
    }

    @Override
    public String execute(Map<String, String> parameters) throws Exception {
        return salesReportService.generateReport(parameters.get("startDate"), parameters.get("endDate"));
    }
}
