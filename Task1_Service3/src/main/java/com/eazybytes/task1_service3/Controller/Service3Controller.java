package com.eazybytes.task1_service3.Controller;

import com.eazybytes.task1_service3.Exceptions.JsonParsingException;
import com.eazybytes.task1_service3.Service.TracingService;
import com.eazybytes.task1_service3.dto.NameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service3")
@Tag(name = "Service 3", description = "Handles name concatenation")
public class Service3Controller {
    private static final Logger logger = LoggerFactory.getLogger(Service3Controller.class);
    private final TracingService tracingService;

    public Service3Controller(TracingService tracingService) {
        this.tracingService = tracingService;
    }

    @PostMapping("/concatNames")
    @Operation(summary = "Concatenates first name and last name")
    public ResponseEntity<String> concatNames(@RequestBody NameDto nameDto, @RequestHeader(value = "traceID", required = false) String traceId) throws JsonParsingException {
        if (traceId == null) traceId = tracingService.generateTraceId();
        tracingService.logRequest("Service3", "/concatNames", traceId);
        if (nameDto.firstName() == null ) {
            logger.error("[Service3] Invalid request | Trace ID: {}", traceId);
            throw new JsonParsingException("firstname");
        }
        if (nameDto.lastName() == null ) {
            logger.error("[Service3] Invalid request | Trace ID: {}", traceId);
            throw new JsonParsingException("lastName");
        }
        tracingService.logResponse("Service3", "/concatNames", traceId);
        return ResponseEntity.ok("" + nameDto.firstName() + " " + nameDto.lastName());

    }
}
