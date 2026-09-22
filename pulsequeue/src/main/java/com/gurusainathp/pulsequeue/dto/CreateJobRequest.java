package com.gurusainathp.pulsequeue.dto;

import java.util.Map;

public record CreateJobRequest(String type, Map<String, String> parameters) {
}
