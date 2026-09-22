package com.gurusainathp.pulsequeue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    private String id;
    private String orderId;
    private String productId;
    private long quantity;
    private BigDecimal netSales;
    private BigDecimal profit;

    public OrderItem(String id, String orderId, String productId, long quantity, BigDecimal netSales,
            BigDecimal profit) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.netSales = netSales;
        this.profit = profit;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public BigDecimal getNetSales() {
        return netSales;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
