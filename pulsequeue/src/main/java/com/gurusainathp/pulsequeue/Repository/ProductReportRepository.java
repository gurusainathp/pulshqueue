package com.gurusainathp.pulsequeue.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gurusainathp.pulsequeue.dto.ProductInfo;
import com.gurusainathp.pulsequeue.dto.ProductSalesOverTime;
import com.gurusainathp.pulsequeue.model.Product;
import com.gurusainathp.pulsequeue.dto.PurchaseSummary;

import java.time.LocalDate;
import java.util.List;

public interface ProductReportRepository extends JpaRepository<Product, String> {
        @Query("SELECT "
                        + "p.productId, p.name, p.category, p.subCategory, p.brand, p.supplier "
                        + "FROM Product p "
                        + "WHERE p.productId = :productId")
        ProductInfo getProductInfo(@Param("productId") String productId);

        @Query("SELECT COUNT(DISTINCT o.orderId), "
                        + "COALESCE(SUM(oi.quantity), 0), "
                        + "COALESCE(SUM(oi.netSales), 0), "
                        + "COALESCE(SUM(oi.profit), 0), "
                        + "CASE WHEN COUNT(DISTINCT o.orderId) > 0 "
                        + "THEN COALESCE(SUM(oi.netSales), 0) / COUNT(DISTINCT o.orderId) "
                        + "ELSE 0 END "
                        + "FROM OrderItem oi "
                        + "JOIN Order o ON oi.orderId = o.orderId "
                        + "WHERE oi.productId = :productId "
                        + "AND o.orderDate BETWEEN :startDate AND :endDate")
        PurchaseSummary getProductPurchaseSummary(@Param("productId") String productId,
                        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

        @Query("SELECT o.orderDate, "
                        + "COALESCE(SUM(oi.quantity), 0), "
                        + "COALESCE(SUM(oi.netSales), 0), "
                        + "COALESCE(SUM(oi.profit), 0) "
                        + "FROM OrderItem oi "
                        + "JOIN Order o ON oi.orderId = o.orderId "
                        + "WHERE oi.productId = :productId "
                        + "AND o.orderDate BETWEEN :startDate AND :endDate "
                        + "GROUP BY o.orderDate "
                        + "ORDER BY o.orderDate ASC")
        List<ProductSalesOverTime> getProductSalesOverTime(@Param("productId") String productId,
                        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}