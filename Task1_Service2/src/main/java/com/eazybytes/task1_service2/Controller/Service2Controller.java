package com.eazybytes.task1_service2.Controller;

import com.eazybytes.task1_service2.Service.TracingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service2")
@Tag(name = "Service 2", description = "Handles greeting message")
public class Service2Controller {
    private static final Logger logger = LoggerFactory.getLogger(Service2Controller.class);
    private final TracingService tracingService;

    public Service2Controller(TracingService tracingService) {
        this.tracingService = tracingService;
    }

    @RequestMapping("/hello")
    @Operation(summary = "Returns Hello message")
    public ResponseEntity<String> hello(@RequestHeader(value = "traceID", required = false) String traceId) {
        if (traceId == null) traceId = tracingService.generateTraceId();
        tracingService.logRequest("Service2", "/hello", traceId);
        tracingService.logResponse("Service2", "/hello", traceId);
        return ResponseEntity.ok("Hello"); };
}
