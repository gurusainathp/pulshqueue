package com.gurusainathp.pulsequeue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String productId;
    private String name;
    private String category;

    public Product(String productId, String name, String category) {
        this.productId = productId;
        this.name = name;
        this.category = category;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }
}
