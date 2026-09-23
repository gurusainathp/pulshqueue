package com.gurusainathp.pulsequeue.dto;

import java.util.List;

public class SalesReport {
    private SalesReportSummary summary;
    private List<ProductSalesSummary> topProducts;

    public SalesReport(SalesReportSummary summary, List<ProductSalesSummary> topProducts) {
        this.summary = summary;
        this.topProducts = topProducts;
    }

    public SalesReportSummary getSummary() {
        return summary;
    }

    public List<ProductSalesSummary> getTopProducts() {
        return topProducts;
    }
}
