package com.gurusainathp.pulsequeue.handler;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component 
public class JobHandlerRegistry {
    private final List<JobHandler> jobHandlers;
    private final Map<String, JobHandler> handlerMap;

    public JobHandlerRegistry(List<JobHandler> jobHandlers) {
        this.jobHandlers = jobHandlers;
        this.handlerMap = new HashMap<>();
        for (JobHandler handler : jobHandlers) {
            this.handlerMap.put(handler.getType(), handler);
        }
    }

    public JobHandler getHandler(String type) {
        if (!handlerMap.containsKey(type)) {
            throw new IllegalArgumentException("No handler found for job type: " + type);
        }
        return handlerMap.get(type);
    }
}
