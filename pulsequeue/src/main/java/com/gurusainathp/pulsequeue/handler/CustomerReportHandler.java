package com.gurusainathp.pulsequeue.handler;

import com.gurusainathp.pulsequeue.service.CustomerReportService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CustomerReportHandler implements JobHandler {

    private final CustomerReportService customerReportService;

    CustomerReportHandler(CustomerReportService customerReportService) {
        this.customerReportService = customerReportService;
    }

    @Override
    public String getType() {
        return "CUSTOMER_REPORT";
    }

    @Override
    public void validate(Map<String, String> parameters) throws IllegalArgumentException {
        String customerId = parameters.get("customerId");
        String startDateStr = parameters.get("startDate");
        String endDateStr = parameters.get("endDate");

        if (customerId == null || customerId.isBlank() || startDateStr == null || startDateStr.isBlank()
                || endDateStr == null || endDateStr.isBlank()) {
            throw new IllegalArgumentException("Missing required parameters: customerId, startDate and endDate");
        }

        if (!customerId.matches("CUST-\\d{6}")) {
            throw new IllegalArgumentException("Invalid customerId");
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
        return customerReportService.getCustomerReport(parameters.get("customerId"), parameters.get("startDate"),
                parameters.get("endDate"));
    }

}
