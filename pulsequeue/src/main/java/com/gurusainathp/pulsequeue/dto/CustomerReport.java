package com.gurusainathp.pulsequeue.dto;

import java.util.List;

public class CustomerReport {
    private CustomerInfo customerInfo;
    private CustomerPurchaseSummary customerPurchaseSummary;
    private List<ProductSalesSummary> topCustomerProducts;
    private List<CustomerOrderSummary> topCustomerOrders;

    public CustomerReport(CustomerInfo customerInfo, CustomerPurchaseSummary customerPurchaseSummary,
            List<ProductSalesSummary> topCustomerProducts, List<CustomerOrderSummary> topCustomerOrders) {
        this.customerInfo = customerInfo;
        this.customerPurchaseSummary = customerPurchaseSummary;
        this.topCustomerProducts = topCustomerProducts;
        this.topCustomerOrders = topCustomerOrders;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public CustomerPurchaseSummary getCustomerPurchaseSummary() {
        return customerPurchaseSummary;
    }

    public List<ProductSalesSummary> getTopCustomerProducts() {
        return topCustomerProducts;
    }

    public List<CustomerOrderSummary> getTopCustomerOrders() {
        return topCustomerOrders;
    }

}
