package com.gurusainathp.pulsequeue.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gurusainathp.pulsequeue.Repository.CustomerSegmentRepository;
import com.gurusainathp.pulsequeue.dto.CustomerSegmentSummary;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class CustomerSegmentReportService {
    private CustomerSegmentRepository customerSegmentRepository;
    private ObjectMapper objectMapper;

    public CustomerSegmentReportService(CustomerSegmentRepository customerSegmentRepository,
            ObjectMapper objectMapper) {
        this.customerSegmentRepository = customerSegmentRepository;
        this.objectMapper = objectMapper;
    }

    public String getCustomerSegmentReport(String startDate, String endDate) {
        List<CustomerSegmentSummary> customerSegmentSummary = customerSegmentRepository
                .getCustomerSegmentSummary(LocalDate.parse(startDate), LocalDate.parse(endDate));

        try {
            return objectMapper.writeValueAsString(customerSegmentSummary);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to generate report JSON", e);
        }
    }
}
