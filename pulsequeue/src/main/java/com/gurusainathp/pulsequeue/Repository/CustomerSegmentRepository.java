package com.gurusainathp.pulsequeue.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gurusainathp.pulsequeue.dto.CustomerSegmentSummary;
import com.gurusainathp.pulsequeue.model.Customer;

public interface CustomerSegmentRepository extends JpaRepository<Customer, String> {
    @Query("SELECT "
            + "c.customerSegment, "
            + "COUNT(DISTINCT c.customerId), "
            + "COUNT(DISTINCT o.orderId), "
            + "COALESCE(SUM(oi.quantity), 0), "
            + "COALESCE(SUM(oi.netSales), 0), "
            + "COALESCE(SUM(oi.profit), 0), "
            + "CASE WHEN COUNT(DISTINCT o.orderId) = 0 THEN 0 "
            + "ELSE SUM(oi.netSales) / COUNT(DISTINCT o.orderId) END, "
            + "CASE WHEN COUNT(DISTINCT c.customerId) = 0 THEN 0 "
            + "ELSE SUM(oi.netSales) / COUNT(DISTINCT c.customerId) END "
            + "FROM Order o "
            + "JOIN Customer c ON o.customerId = c.customerId "
            + "JOIN OrderItem oi ON o.orderId = oi.orderId "
            + "WHERE o.orderDate BETWEEN :startDate AND :endDate "
            + "GROUP BY c.customerSegment "
            + "ORDER BY SUM(oi.netSales) DESC")
    List<CustomerSegmentSummary> getCustomerSegmentSummary(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}