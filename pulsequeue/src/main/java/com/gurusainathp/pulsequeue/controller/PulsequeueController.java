package com.gurusainathp.pulsequeue.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class PulsequeueController {
    @GetMapping("/hello")
    public String getHello() {
        return new String("Hello, PulseQueue!");
    }

    @PostMapping ("/jobs")
    public String createJob() {
        return new String("Job created successfully!");
    }
}
