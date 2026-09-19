package com.gurusainathp.pulsequeue.worker;

import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.gurusainathp.pulsequeue.service.JobService;
import com.gurusainathp.pulsequeue.model.Job;
import com.gurusainathp.pulsequeue.model.Status;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;

@Component
public class JobWorker {
    private final JobService jobService;
    private final StringRedisTemplate redisTemplate;

    public JobWorker(JobService jobService, StringRedisTemplate redisTemplate) {
        this.jobService = jobService;
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedDelay = 10000)
    public void processJobs() {
        String jobIdStr = redisTemplate.opsForList().leftPop("job_queue");
        if (jobIdStr != null) {
            Job job = jobService.getJobById(UUID.fromString(jobIdStr));
            if (job != null) {
                job.setStatus(Status.PROCESSING);
                job.setStartedAt(new Timestamp(System.currentTimeMillis()));
                jobService.updateJob(job);
            }
            // Simulate job processing
            try {
                Thread.sleep(20000); // Simulate processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (job != null) {
                job.setStatus(Status.COMPLETED);
                job.setCompletedAt(new Timestamp(System.currentTimeMillis()));
                jobService.updateJob(job);
            }
        }
    }
}
