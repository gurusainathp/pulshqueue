package com.gurusainathp.pulsequeue.dto;

import java.util.List;

public class CustomerSegmentReport {
    private List<CustomerSegmentSummary> customerSegmentSummary;

    public CustomerSegmentReport(List<CustomerSegmentSummary> customerSegmentSummary) {
        this.customerSegmentSummary = customerSegmentSummary;
    }

    public List<CustomerSegmentSummary> getCustomerSegmentSummary() {
        return customerSegmentSummary;
    }
}
