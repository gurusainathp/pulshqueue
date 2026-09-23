package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesOverTimeSummary {
    private LocalDate date;
    private BigDecimal sales;
    private BigDecimal profit;

    public SalesOverTimeSummary(LocalDate date, BigDecimal sales, BigDecimal profit) {
        this.date = date;
        this.sales = sales;
        this.profit = profit;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
