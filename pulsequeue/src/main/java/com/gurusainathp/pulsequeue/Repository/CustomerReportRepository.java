package com.gurusainathp.pulsequeue.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import com.gurusainathp.pulsequeue.dto.CustomerInfo;
import com.gurusainathp.pulsequeue.dto.CustomerOrderSummary;
import com.gurusainathp.pulsequeue.dto.CustomerPurchaseSummary;
import com.gurusainathp.pulsequeue.dto.ProductSalesSummary;
import com.gurusainathp.pulsequeue.model.Customer;
import java.time.LocalDate;
import java.util.List;

public interface CustomerReportRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c.customerId, c.customerName, c.customerSegment, c.customerCountry, c.customerState, c.customerCity "
            +
            "FROM Customer c " +
            "WHERE c.customerId = :customerId")
    CustomerInfo getCustomerInfo(@Param("customerId") String customerId);

    @Query("SELECT COUNT(DISTINCT o.orderId), "
            + "COALESCE(SUM(oi.quantity), 0), "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0), "
            + "CASE WHEN COUNT(DISTINCT o.orderId) > 0 "
            + "THEN COALESCE(SUM(oi.netSales), 0) / COUNT(DISTINCT o.orderId) "
            + "ELSE 0 END "
            + "FROM OrderItem oi "
            + "JOIN Order o ON oi.orderId = o.orderId "
            + "WHERE o.customerId = :customerId "
            + "AND o.orderDate BETWEEN :startDate AND :endDate")
    CustomerPurchaseSummary getCustomerPurchaseSummary(@Param("customerId") String customerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT oi.productId, p.name, "
            + "COALESCE(SUM(oi.quantity), 0), "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0) "
            + "FROM OrderItem oi "
            + "JOIN Product p ON oi.productId = p.productId "
            + "JOIN Order o ON oi.orderId = o.orderId "
            + "WHERE o.customerId = :customerId "
            + "AND o.orderDate BETWEEN :startDate AND :endDate "
            + "GROUP BY oi.productId, p.name "
            + "ORDER BY COALESCE(SUM(oi.netSales), 0) DESC")
    List<ProductSalesSummary> getTopCustomerProducts(@Param("customerId") String customerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

    @Query("SELECT o.orderId, o.orderDate, o.orderStatus, "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0) "
            + "FROM OrderItem oi "
            + "JOIN Order o ON oi.orderId = o.orderId "
            + "WHERE o.customerId = :customerId "
            + "AND o.orderDate BETWEEN :startDate AND :endDate "
            + "GROUP BY o.orderId, o.orderDate, o.orderStatus "
            + "ORDER BY o.orderDate DESC")
    List<CustomerOrderSummary> getTopCustomerOrders(@Param("customerId") String customerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);
}
