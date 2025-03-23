package com.eazybytes.task1_service1.Controller;

import com.eazybytes.task1_service1.Service.TracingService;
import com.eazybytes.task1_service1.dto.NameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service1")
@Tag(name = "Service 1", description = "Handles orchestration")
public class Service1Controller {

    private RestTemplate restTemplate;
    private TracingService tracingService;
    private static final Logger logger = LoggerFactory.getLogger(Service1Controller.class);
    @Autowired
    public Service1Controller(TracingService tracingService, RestTemplate restTemplate) {
        this.tracingService = tracingService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/status")
    @Operation(summary = "Check if service is running")
    public ResponseEntity<String> getApplicationStatus() {
        String traceId = tracingService.generateTraceId();
        tracingService.logRequest("Service1", "/status", traceId);
        tracingService.logResponse("Service1", "/status", traceId);
        return ResponseEntity.status(200).body("Up");
    }
    @PostMapping("/message")
    @Operation(summary = "Processes message using Service 2 & 3")
    public ResponseEntity<String> getMessage(@RequestBody NameDto nameDto) {
        String traceId = tracingService.generateTraceId();
        tracingService.logRequest("Service1", "/message", traceId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("traceId", traceId);

        // GET call to Service 2
        HttpEntity<Void> getRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> helloResponse = restTemplate.exchange(
                "http://localhost:8081/service2/hello",
                HttpMethod.GET,
                getRequestEntity,
                String.class
        );

        String hello = helloResponse.getBody();
        // POST call to Service 3 
        HttpEntity<NameDto> postRequestEntity = new HttpEntity<>(nameDto, headers);
        ResponseEntity<String> fullNameResponse = restTemplate.exchange(
                "http://localhost:8082/service3/concatNames",
                HttpMethod.POST,
                postRequestEntity,
                String.class
        );

        String fullName = fullNameResponse.getBody();
        tracingService.logResponse("Service1", "/message", traceId);
        return ResponseEntity.ok(hello + " " + fullName);

    }




}
