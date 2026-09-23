package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;

public class ProductSalesSummary {
    private String productId;
    private String productName;
    private long quantitySold;
    private BigDecimal sales;
    private BigDecimal profit;

    public ProductSalesSummary(String productId, String productName, long quantitySold, BigDecimal sales, BigDecimal profit) {
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.sales = sales;
        this.profit = profit;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
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
