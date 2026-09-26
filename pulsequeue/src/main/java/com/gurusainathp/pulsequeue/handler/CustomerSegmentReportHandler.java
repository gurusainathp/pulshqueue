package com.gurusainathp.pulsequeue.handler;

import com.gurusainathp.pulsequeue.service.CustomerSegmentReportService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CustomerSegmentReportHandler implements JobHandler {

    private final CustomerSegmentReportService customerSegmentReportService;

    CustomerSegmentReportHandler(CustomerSegmentReportService customerSegmentReportService) {
        this.customerSegmentReportService = customerSegmentReportService;
    }

    @Override
    public String getType() {
        return "CUSTOMER_SEGMENT_REPORT";
    }

    @Override
    public void validate(Map<String, String> parameters) throws IllegalArgumentException {
        String startDateStr = parameters.get("startDate");
        String endDateStr = parameters.get("endDate");

        if (startDateStr == null || startDateStr.isBlank()
                || endDateStr == null || endDateStr.isBlank()) {
            throw new IllegalArgumentException("Missing required parameters: customerId, startDate and endDate");
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
        return customerSegmentReportService.getCustomerSegmentReport(parameters.get("startDate"),
                parameters.get("endDate"));
    }

}
