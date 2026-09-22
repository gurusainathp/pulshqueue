package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import com.gurusainathp.pulsequeue.Repository.JobRepository;
import com.gurusainathp.pulsequeue.dto.CreateJobRequest;
import com.gurusainathp.pulsequeue.model.Job;
import java.util.UUID;
import java.sql.Timestamp;
import com.gurusainathp.pulsequeue.model.Status;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobQueueService jobQueueService;

    public JobService(JobRepository jobRepository, JobQueueService jobQueueService) {
        this.jobRepository = jobRepository;
        this.jobQueueService = jobQueueService;
    }

    public Job createJob(CreateJobRequest request) {
        Job job = jobRepository.save(new Job(request.type(), request.parameters()));
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

    public void startJob(Job job, UUID workerId) {
        job.setStatus(Status.PROCESSING);
        job.setStartedAt(new Timestamp(System.currentTimeMillis()));
        job.setWorkerId(workerId);
        updateJob(job);
    }

    public void completeJob(Job job, String result) {
        job.setStatus(Status.COMPLETED);
        job.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        job.setResult(result);
        job.setError(null);
        updateJob(job);
    }

    public void failJob(Job job, String error) {
        job.setStatus(Status.FAILED);
        job.setError(error);
        job.setAttempts(job.getAttempts() + 1);
        if (job.getAttempts() < 3) {
            job.setStatus(Status.QUEUED);
            jobQueueService.enqueueJob(job.getId());
        }
        updateJob(job);
    }
}
