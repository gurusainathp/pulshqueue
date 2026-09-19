package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.JobRepository;
import com.gurusainathp.pulsequeue.dto.CreateJobRequest;
import com.gurusainathp.pulsequeue.model.Job;
import java.util.UUID;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobQueueService jobQueueService;

    public JobService(JobRepository jobRepository, JobQueueService jobQueueService) {
        this.jobRepository = jobRepository;
        this.jobQueueService = jobQueueService;
    }

    public Job createJob(CreateJobRequest request) {
        Job job = jobRepository.save(new Job(request.type()));
        jobQueueService.enqueueJob(job.getId());
        return job;
    }

    public Job getJobById(UUID id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Iterable<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public void deleteJob(Job job) {
        jobRepository.delete(job);
    }

    public void updateJob(Job job) {
        jobRepository.save(job);
    }
}
