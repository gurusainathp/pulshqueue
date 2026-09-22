package com.gurusainathp.pulsequeue.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component 
public class SalesReportHandler implements JobHandler {
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
        return "Sales report generated for period: " + parameters.get("startDate") + " to " + parameters.get("endDate");
    }
}
