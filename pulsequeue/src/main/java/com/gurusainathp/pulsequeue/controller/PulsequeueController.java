package com.gurusainathp.pulsequeue.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.gurusainathp.pulsequeue.service.JobService;
import com.gurusainathp.pulsequeue.model.Job;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.UUID;
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

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable UUID id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return ResponseEntity.ok(job);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<Iterable<Job>> getAllJobs() {
        Iterable<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody CreateJobRequest request) {
        Job job = jobService.createJob(request);
        return ResponseEntity.created(URI.create("/jobs/" + job.getId())).body(job);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            jobService.deleteJob(job);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
