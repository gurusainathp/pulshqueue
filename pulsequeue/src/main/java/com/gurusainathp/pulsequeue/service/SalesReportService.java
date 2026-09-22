package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.SalesReportRepository;

@Service 
public class SalesReportService {
    private final SalesReportRepository salesReportRepository;

    public SalesReportService(SalesReportRepository salesReportRepository) {
        this.salesReportRepository = salesReportRepository;
    }

    public String generateReport(String startDate, String endDate) {
        long count = salesReportRepository.countByOrderDateBetween(java.time.LocalDate.parse(startDate), java.time.LocalDate.parse(endDate));
        return "Sales report from " + startDate + " to " + endDate + ": " + count + " orders";
    }
}