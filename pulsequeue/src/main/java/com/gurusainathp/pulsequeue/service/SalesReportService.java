package com.gurusainathp.pulsequeue.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.SalesReportRepository;
import com.gurusainathp.pulsequeue.dto.CategorySalesSummary;
import com.gurusainathp.pulsequeue.dto.ProductSalesSummary;
import com.gurusainathp.pulsequeue.dto.SalesOverTimeSummary;
import com.gurusainathp.pulsequeue.dto.SalesReport;
import com.gurusainathp.pulsequeue.dto.SalesReportSummary;
import java.time.LocalDate;

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
        SalesReportSummary summary = salesReportRepository.getSalesReportSummary(LocalDate.parse(startDate),
                LocalDate.parse(endDate));
        List<ProductSalesSummary> topProducts = salesReportRepository.getProductSalesSummary(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                PageRequest.of(0, 10));
        List<CategorySalesSummary> topCategories = salesReportRepository.getCategorySalesSummary(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                PageRequest.of(0, 10));
        List<SalesOverTimeSummary> salesOverTime = salesReportRepository.getSalesOverTimeSummary(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                PageRequest.of(0, 10));
        SalesReport report = new SalesReport(summary, topProducts, topCategories, salesOverTime);

        try {
            return objectMapper.writeValueAsString(report);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to generate report JSON", e);
        }
    }
}