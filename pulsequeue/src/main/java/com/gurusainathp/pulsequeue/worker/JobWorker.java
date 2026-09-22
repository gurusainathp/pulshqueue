package com.gurusainathp.pulsequeue.worker;

import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.gurusainathp.pulsequeue.service.JobService;
import com.gurusainathp.pulsequeue.model.Job;
import java.util.UUID;
import com.gurusainathp.pulsequeue.handler.JobHandlerRegistry;
import com.gurusainathp.pulsequeue.handler.JobHandler;

import org.springframework.scheduling.annotation.Scheduled;

@Component
public class JobWorker {
    private final UUID workerId;
    private final JobService jobService;
    private final StringRedisTemplate redisTemplate;
    private final JobHandlerRegistry jobHandlerRegistry;

    public JobWorker(JobService jobService, StringRedisTemplate redisTemplate, JobHandlerRegistry jobHandlerRegistry) {
        this.workerId = UUID.randomUUID();
        this.jobService = jobService;
        this.redisTemplate = redisTemplate;
        this.jobHandlerRegistry = jobHandlerRegistry;
    }

    @Scheduled(fixedDelay = 10000)
    public void processJobs() {
        String jobIdStr = redisTemplate.opsForList().leftPop("job_queue");
        if (jobIdStr != null) {
            Job job = jobService.getJobById(UUID.fromString(jobIdStr));
            if (job == null) {
                return;
            }
            
            jobService.startJob(job, workerId);

            try {
                JobHandler handler = jobHandlerRegistry.getHandler(job.getType());
                handler.validate(job.getParameters());
                String result = handler.execute(job.getParameters());
                jobService.completeJob(job, result);
            } catch (Exception e) {
                jobService.failJob(job, e.getMessage());
            }
        }
    }
}
