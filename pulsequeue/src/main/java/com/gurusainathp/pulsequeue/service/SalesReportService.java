package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.SalesReportRepository;
import com.gurusainathp.pulsequeue.dto.SalesReportSummary;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class SalesReportService {
    private final SalesReportRepository salesReportRepository;
    private final ObjectMapper objectMapper;

    public SalesReportService(SalesReportRepository salesReportRepository, ObjectMapper objectMapper) {
        this.salesReportRepository = salesReportRepository;
        this.objectMapper = objectMapper;
    }

    public String generateReport(String startDate, String endDate) {
        SalesReportSummary summary = salesReportRepository.getSalesReportSummary(java.time.LocalDate.parse(startDate),
                java.time.LocalDate.parse(endDate));

        try {
            return objectMapper.writeValueAsString(summary);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to generate report JSON", e);
        }
    }
}