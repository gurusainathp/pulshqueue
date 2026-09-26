package com.gurusainathp.pulsequeue.handler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gurusainathp.pulsequeue.service.ProductReportService;

@Component
public class ProductReportHandler implements JobHandler {
    private ProductReportService productReportService;

    public ProductReportHandler(ProductReportService productReportService) {
        this.productReportService = productReportService;
    }

    @Override
    public String getType() {
        return "PRODUCT_REPORT";
    }

    @Override
    public void validate(Map<String, String> parameters) throws IllegalArgumentException {
        String productId = parameters.get("productId");
        String startDateStr = parameters.get("startDate");
        String endDateStr = parameters.get("endDate");

        if (productId == null || productId.isBlank() || startDateStr == null || startDateStr.isBlank()
                || endDateStr == null || endDateStr.isBlank()) {
            throw new IllegalArgumentException("Missing required parameters: productId, startDate and endDate");
        }

        if (!productId.matches("PROD-\\d{6}")) {
            throw new IllegalArgumentException("Invalid productId");
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
        return productReportService.getProductReport(parameters.get("productId"), parameters.get("startDate"),
                parameters.get("endDate"));
    }
}
