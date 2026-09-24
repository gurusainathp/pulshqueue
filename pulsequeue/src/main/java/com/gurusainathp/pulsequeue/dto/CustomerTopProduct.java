package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;

public class CustomerTopProduct {
    private String productId;
    private String productName;
    private long quantityPurchased;
    private BigDecimal sales;

    public CustomerTopProduct(String productId, String productName, long quantityPurchased, BigDecimal sales) {
        this.productId = productId;
        this.productName = productName;
        this.quantityPurchased = quantityPurchased;
        this.sales = sales;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantityPurchased() {
        return quantityPurchased;
    }

    public BigDecimal getSales() {
        return sales;
    }

}
