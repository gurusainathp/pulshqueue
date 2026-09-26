package com.gurusainathp.pulsequeue.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gurusainathp.pulsequeue.Repository.ProductReportRepository;
import com.gurusainathp.pulsequeue.dto.ProductInfo;
import com.gurusainathp.pulsequeue.dto.ProductReport;
import com.gurusainathp.pulsequeue.dto.ProductSalesOverTime;
import com.gurusainathp.pulsequeue.dto.PurchaseSummary;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class ProductReportService {
    private final ProductReportRepository productReportRepository;
    private final ObjectMapper objectMapper;

    public ProductReportService(ProductReportRepository productReportRepository, ObjectMapper objectMapper) {
        this.productReportRepository = productReportRepository;
        this.objectMapper = objectMapper;
    }

    public String getProductReport(String productId, String startDate, String endDate) {
        ProductInfo productInfo = productReportRepository.getProductInfo(productId);
        PurchaseSummary productPurchaseSummary = productReportRepository.getProductPurchaseSummary(productId,
                LocalDate.parse(startDate), LocalDate.parse(endDate));
        List<ProductSalesOverTime> productSalesOverTime = productReportRepository.getProductSalesOverTime(productId,
                LocalDate.parse(startDate), LocalDate.parse(endDate));

        ProductReport productReport = new ProductReport(productInfo, productPurchaseSummary, productSalesOverTime);

        try {
            return objectMapper.writeValueAsString(productReport);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to generate report JSON", e);
        }
    }
}
