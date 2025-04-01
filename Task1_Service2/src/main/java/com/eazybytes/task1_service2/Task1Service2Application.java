package com.eazybytes.task1_service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Task1Service2Application {

    public static void main(String[] args) {
        SpringApplication.run(Task1Service2Application.class, args);
    }

}
