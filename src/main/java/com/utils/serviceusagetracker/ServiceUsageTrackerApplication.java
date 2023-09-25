package com.utils.serviceusagetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServiceUsageTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUsageTrackerApplication.class, args);
    }

}
