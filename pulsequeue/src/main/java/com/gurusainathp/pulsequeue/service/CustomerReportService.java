package com.gurusainathp.pulsequeue.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gurusainathp.pulsequeue.Repository.CustomerReportRepository;
import com.gurusainathp.pulsequeue.dto.CustomerInfo;
import com.gurusainathp.pulsequeue.dto.CustomerOrderSummary;
import com.gurusainathp.pulsequeue.dto.PurchaseSummary;
import com.gurusainathp.pulsequeue.dto.CustomerReport;
import com.gurusainathp.pulsequeue.dto.ProductSalesSummary;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class CustomerReportService {
    private final CustomerReportRepository customerReportRepository;
    private final ObjectMapper objectMapper;

    public CustomerReportService(CustomerReportRepository customerReportRepository, ObjectMapper objectMapper) {
        this.customerReportRepository = customerReportRepository;
        this.objectMapper = objectMapper;
    }

    public String getCustomerReport(String customerId, String startDate, String endDate) {
        CustomerInfo customerInfo = customerReportRepository.getCustomerInfo(customerId);
        PurchaseSummary customerPurchaseSummary = customerReportRepository
                .getCustomerPurchaseSummary(customerId, LocalDate.parse(startDate), LocalDate.parse(endDate));
        List<ProductSalesSummary> topCustomerProducts = customerReportRepository.getTopCustomerProducts(customerId,
                LocalDate.parse(startDate), LocalDate.parse(endDate), PageRequest.of(0, 10));
        List<CustomerOrderSummary> topCustomerOrders = customerReportRepository.getTopCustomerOrders(customerId,
                LocalDate.parse(startDate), LocalDate.parse(endDate), PageRequest.of(0, 10));

        CustomerReport customerReport = new CustomerReport(customerInfo, customerPurchaseSummary,
                topCustomerProducts, topCustomerOrders);

        try {
            return objectMapper.writeValueAsString(customerReport);
        } catch (JacksonException e) {

            throw new RuntimeException("Failed to generate report JSON", e);
        }
    }
}
