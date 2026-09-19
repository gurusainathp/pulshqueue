package com.gurusainathp.pulsequeue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
public class Job {
    @Id
    private UUID id;
    private String type;
    @Enumerated(EnumType.ORDINAL)
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

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public int getAttempts() {
        return attempts;
    }

    public UUID getWorkerId() {
        return workerId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
