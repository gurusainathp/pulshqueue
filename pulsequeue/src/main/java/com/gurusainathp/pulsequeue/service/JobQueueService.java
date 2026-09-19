package com.gurusainathp.pulsequeue.service;

import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.UUID;

@Service
public class JobQueueService {
    private static final String QUEUE_KEY = "job_queue";
    private final StringRedisTemplate redisTemplate;

    public JobQueueService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void enqueueJob(UUID jobId) {
        redisTemplate.opsForList().rightPush(QUEUE_KEY, jobId.toString());
    }
}
