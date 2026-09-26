package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;

public class CustomerSegmentSummary {
    private String segment;
    private Long customerCount;
    private Long totalOrders;
    private Long totalQuantity;
    private BigDecimal totalSales;
    private BigDecimal totalProfit;
    private BigDecimal averageOrderValue;
    private BigDecimal averageCustomerSales;

    public CustomerSegmentSummary(String segment, Long customerCount, Long totalOrders, Long totalQuantity,
            BigDecimal totalSales,
            BigDecimal totalProfit, BigDecimal averageOrderValue, BigDecimal averageCustomerSales) {
        this.segment = segment;
        this.customerCount = customerCount;
        this.totalOrders = totalOrders;
        this.totalQuantity = totalQuantity;
        this.totalSales = totalSales;
        this.totalProfit = totalProfit;
        this.averageOrderValue = averageOrderValue;
        this.averageCustomerSales = averageCustomerSales;
    }

    public String getSegment() {
        return segment;
    }

    public Long getCustomerCount() {
        return customerCount;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public BigDecimal getAverageOrderValue() {
        return averageOrderValue;
    }

    public BigDecimal getAverageCustomerSales() {
        return averageCustomerSales;
    }

}
