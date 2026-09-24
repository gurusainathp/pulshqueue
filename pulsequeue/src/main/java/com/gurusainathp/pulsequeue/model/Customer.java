package com.gurusainathp.pulsequeue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "customers")
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String customerSegment;
    private String customerCountry;
    private String customerState;
    private String customerCity;

    public Customer(String customerId, String customerName, String customerSegment, String customerCountry,
            String customerState, String customerCity) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSegment = customerSegment;
        this.customerCountry = customerCountry;
        this.customerState = customerState;
        this.customerCity = customerCity;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getCustomerSegment() {
        return this.customerSegment;
    }

    public String getCustomerCountry() {
        return this.customerCountry;
    }

    public String getCustomerState() {
        return this.customerState;
    }

    public String getCustomerCity() {
        return this.customerCity;
    }
}
