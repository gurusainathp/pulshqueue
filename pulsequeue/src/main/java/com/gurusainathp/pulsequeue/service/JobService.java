package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.JobRepository;
import com.gurusainathp.pulsequeue.dto.CreateJobRequest;
import com.gurusainathp.pulsequeue.model.Job;
import java.util.UUID;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public Job createJob(CreateJobRequest request) {
        Job job = new Job(request.type());
        return jobRepository.save(job);
    }

    public Job getJobById(UUID id) {
        return jobRepository.findById(id).orElse(null);
    }
}
