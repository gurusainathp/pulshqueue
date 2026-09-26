package com.gurusainathp.pulsequeue.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public class ProductSalesOverTime {
    private LocalDate date;
    private Long quantitySold;
    private BigDecimal sales;
    private BigDecimal profit;

    public ProductSalesOverTime(LocalDate date, Long quantitySold, BigDecimal sales, BigDecimal profit) {
        this.date = date;
        this.quantitySold = quantitySold;
        this.sales = sales;
        this.profit = profit;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getQuantitySold() {
        return quantitySold;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}