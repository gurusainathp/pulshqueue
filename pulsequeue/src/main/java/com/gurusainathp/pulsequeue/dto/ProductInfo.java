package com.gurusainathp.pulsequeue.dto;

public class ProductInfo {
    private String productId;
    private String name;
    private String category;
    private String subCategory;
    private String brand;
    private String supplier;

    public ProductInfo(String productId, String name, String category, String subCategory, String brand,
            String supplier) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.brand = brand;
        this.supplier = supplier;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public String getSupplier() {
        return supplier;
    }

}
