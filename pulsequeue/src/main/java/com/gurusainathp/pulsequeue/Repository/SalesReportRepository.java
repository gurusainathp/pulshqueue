package com.gurusainathp.pulsequeue.Repository;

import com.gurusainathp.pulsequeue.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface SalesReportRepository extends JpaRepository<Order, String> {
    long countByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}