package com.gurusainathp.pulsequeue.Repository;

import com.gurusainathp.pulsequeue.dto.SalesReportSummary;
import com.gurusainathp.pulsequeue.model.Order;
import com.gurusainathp.pulsequeue.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SalesReportRepository extends JpaRepository<Order, String> {

    @Query("SELECT "
            + "COUNT(DISTINCT o.orderId), SUM(oi.netSales), SUM(oi.profit), SUM(oi.netSales) / COUNT(DISTINCT o.orderId) "
            + "from Order o join OrderItem oi on o.orderId = oi.orderId "
            + "WHERE o.orderDate BETWEEN :startDate AND :endDate")

    SalesReportSummary getSalesReportSummary(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}