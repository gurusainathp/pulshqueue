package com.gurusainathp.pulsequeue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
public class Job {
    @Id
    private UUID id;
    private String type;
    private Status status;
    private Timestamp createdAt;
    private Timestamp startedAt;
    private Timestamp completedAt;
    private String result;
    private String error;
    private int attempts;
    private UUID workerId;

    public Job() {
        this.id = UUID.randomUUID();
        this.status = Status.QUEUED;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.attempts = 0;
    }

    public Job(String type) {
        this();
        this.type = type;
    }
}
