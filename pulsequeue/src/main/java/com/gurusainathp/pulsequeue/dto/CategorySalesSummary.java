package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;

public class CategorySalesSummary {
    private String category;
    private long quantitySold;
    private BigDecimal sales;
    private BigDecimal profit;

    public CategorySalesSummary(String category, long quantitySold, BigDecimal sales, BigDecimal profit) {
        this.category = category;
        this.quantitySold = quantitySold;
        this.sales = sales;
        this.profit = profit;
    }

    public String getCategory() {
        return category;
    }

    public long getQuantitySold() {
        return quantitySold;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
