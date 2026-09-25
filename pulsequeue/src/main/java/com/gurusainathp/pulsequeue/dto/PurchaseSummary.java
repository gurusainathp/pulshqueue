package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;

public class PurchaseSummary {
    private Long totalOrders;
    private Long totalQuantity;
    private BigDecimal totalSales;
    private BigDecimal totalProfit;
    private BigDecimal averageOrderValue;

    public PurchaseSummary(Long totalOrders, Long totalQuantity, BigDecimal totalSales, BigDecimal totalProfit,
            BigDecimal averageOrderValue) {
        this.totalOrders = totalOrders;
        this.totalQuantity = totalQuantity;
        this.totalSales = totalSales;
        this.totalProfit = totalProfit;
        this.averageOrderValue = averageOrderValue;
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

}
