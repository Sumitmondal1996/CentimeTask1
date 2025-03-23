package com.eazybytes.task1_service2.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TracingService {
    private static final Logger logger = LoggerFactory.getLogger(TracingService.class);

    public String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public void logRequest(String serviceName, String endpoint, String traceId) {
        logger.info("[{}] Incoming request to {} | Trace ID: {}", serviceName, endpoint, traceId);
    }

    public void logResponse(String serviceName, String endpoint, String traceId) {
        logger.info("[{}] Completed request to {} | Trace ID: {}", serviceName, endpoint, traceId);
    }
}

