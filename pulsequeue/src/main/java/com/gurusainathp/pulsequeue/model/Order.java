package com.gurusainathp.pulsequeue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private LocalDate orderDate;
    private String orderStatus;
    private String customerId;

    public Order() {
    }

    public Order(String orderId, LocalDate orderDate, String orderStatus, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
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

    public String getCustomerId() {
        return customerId;
    }
}
