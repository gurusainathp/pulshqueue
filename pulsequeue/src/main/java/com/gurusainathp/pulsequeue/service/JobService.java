package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.JobRepository;
import com.gurusainathp.pulsequeue.dto.CreateJobRequest;
import com.gurusainathp.pulsequeue.model.Job;

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
}
