package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;

public class SalesReportSummary {
    private long totalOrders;
    private BigDecimal totalSales;
    private BigDecimal totalProfit;
    private BigDecimal averageOrderValue;

    public SalesReportSummary(long totalOrders, BigDecimal totalSales, BigDecimal totalProfit,
            BigDecimal averageOrderValue) {
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
        this.totalProfit = totalProfit;
        this.averageOrderValue = averageOrderValue;
    }

    public long getTotalOrders() {
        return totalOrders;
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
