package com.gurusainathp.pulsequeue.handler;

import java.util.Map;

public interface JobHandler {
    String getType();

    void validate(Map<String, String> parameters) throws IllegalArgumentException;

    String execute(Map<String, String> parameters) throws Exception;
}
