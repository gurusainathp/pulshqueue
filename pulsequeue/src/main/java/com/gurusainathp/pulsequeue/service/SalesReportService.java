package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;

@Service 
public class SalesReportService {
    public String generateReport(String startDate, String endDate) {
        // Simulate report generation logic
        return "Sales report from " + startDate + " to " + endDate;
    }
}