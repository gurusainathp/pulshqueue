package com.gurusainathp.pulsequeue.Repository;

import com.gurusainathp.pulsequeue.dto.CategorySalesSummary;
import com.gurusainathp.pulsequeue.dto.ProductSalesSummary;
import com.gurusainathp.pulsequeue.dto.SalesOverTimeSummary;
import com.gurusainathp.pulsequeue.dto.SalesReportSummary;
import com.gurusainathp.pulsequeue.model.Order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import java.time.LocalDate;

public interface SalesReportRepository extends JpaRepository<Order, String> {

        @Query("SELECT "
            + "COUNT(DISTINCT o.orderId), "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0), "
            + "CASE WHEN COUNT(DISTINCT o.orderId) > 0 "
            + "THEN COALESCE(SUM(oi.netSales), 0) / COUNT(DISTINCT o.orderId) "
            + "ELSE 0 END "
            + "FROM Order o "
            + "JOIN OrderItem oi ON o.orderId = oi.orderId "
            + "WHERE o.orderDate BETWEEN :startDate AND :endDate")
        SalesReportSummary getSalesReportSummary(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query("SELECT oi.productId, p.name, "
            + "COALESCE(SUM(oi.quantity), 0), "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0) "
            + "FROM OrderItem oi "
            + "JOIN Product p ON oi.productId = p.productId "
            + "JOIN Order o ON oi.orderId = o.orderId "
            + "WHERE o.orderDate BETWEEN :startDate AND :endDate "
            + "GROUP BY oi.productId, p.name "
            + "ORDER BY COALESCE(SUM(oi.netSales), 0) DESC")
        List<ProductSalesSummary> getProductSalesSummary(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate, Pageable pageable);

        @Query("SELECT p.category, "
            + "COALESCE(SUM(oi.quantity), 0), "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0) "
            + "FROM OrderItem oi "
            + "JOIN Product p ON oi.productId = p.productId "
            + "JOIN Order o ON oi.orderId = o.orderId "
            + "WHERE o.orderDate BETWEEN :startDate AND :endDate "
            + "GROUP BY p.category "
            + "ORDER BY COALESCE(SUM(oi.netSales), 0) DESC")
        List<CategorySalesSummary> getCategorySalesSummary(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate, Pageable pageable);

        @Query("SELECT o.orderDate, "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0) "
            + "FROM OrderItem oi "
            + "JOIN Order o ON oi.orderId = o.orderId "
            + "WHERE o.orderDate BETWEEN :startDate AND :endDate "
            + "GROUP BY o.orderDate "
            + "ORDER BY o.orderDate ASC")
        List<SalesOverTimeSummary> getSalesOverTimeSummary(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);
}