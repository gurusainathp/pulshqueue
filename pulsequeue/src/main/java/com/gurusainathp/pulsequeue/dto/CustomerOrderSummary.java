package com.gurusainathp.pulsequeue.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerOrderSummary {
    private String orderId;
    private LocalDate orderDate;
    private String orderStatus;
    private BigDecimal sales;
    private BigDecimal profit;

    public CustomerOrderSummary(String orderId, LocalDate orderDate, String orderStatus, BigDecimal sales,
            BigDecimal profit) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.sales = sales;
        this.profit = profit;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public BigDecimal getsales() {
        return sales;
    }

    public BigDecimal getprofit() {
        return profit;
    }

}
