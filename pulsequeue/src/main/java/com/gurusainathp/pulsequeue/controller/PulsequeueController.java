package com.gurusainathp.pulsequeue.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.gurusainathp.pulsequeue.service.JobService;

import com.gurusainathp.pulsequeue.dto.CreateJobRequest;

@RestController
public class PulsequeueController {
    private final JobService jobService;

    public PulsequeueController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/hello")
    public String getHello() {
        return new String("Hello, PulseQueue!");
    }

    @PostMapping ("/jobs")
    public String createJob(@RequestBody CreateJobRequest request) {
        jobService.createJob(request);
        return "Job created successfully!";
    }
}
