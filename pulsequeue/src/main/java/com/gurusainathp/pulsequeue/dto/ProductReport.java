package com.gurusainathp.pulsequeue.dto;

import java.util.List;

public class ProductReport {
    private ProductInfo productInfo;
    private PurchaseSummary productPurchaseSummary;
    private List<ProductSalesOverTime> productSalesOverTime;

    public ProductReport(ProductInfo productInfo, PurchaseSummary productPurchaseSummary,
            List<ProductSalesOverTime> productSalesOverTime) {
        this.productInfo = productInfo;
        this.productPurchaseSummary = productPurchaseSummary;
        this.productSalesOverTime = productSalesOverTime;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public PurchaseSummary getProductPurchaseSummary() {
        return productPurchaseSummary;
    }

    public List<ProductSalesOverTime> getProductSalesOverTime() {
        return productSalesOverTime;
    }

}
