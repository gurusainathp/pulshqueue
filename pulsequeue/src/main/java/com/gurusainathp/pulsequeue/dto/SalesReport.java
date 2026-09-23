package com.gurusainathp.pulsequeue.dto;

import java.util.List;

public class SalesReport {
    private SalesReportSummary summary;
    private List<ProductSalesSummary> topProducts;
    private List<CategorySalesSummary> topCategories;
    private List<SalesOverTimeSummary> salesOverTime;

    public SalesReport(SalesReportSummary summary, List<ProductSalesSummary> topProducts,
            List<CategorySalesSummary> topCategories, List<SalesOverTimeSummary> salesOverTime) {
        this.summary = summary;
        this.topProducts = topProducts;
        this.topCategories = topCategories;
        this.salesOverTime = salesOverTime;
    }

    public SalesReportSummary getSummary() {
        return summary;
    }

    public List<ProductSalesSummary> getTopProducts() {
        return topProducts;
    }

    public List<CategorySalesSummary> getTopCategories() {
        return topCategories;
    }

    public List<SalesOverTimeSummary> getSalesOverTime() {
        return salesOverTime;
    }
}
