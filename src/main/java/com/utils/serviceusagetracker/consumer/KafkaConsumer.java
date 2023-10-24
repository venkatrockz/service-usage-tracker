package com.utils.serviceusagetracker.consumer;

import com.google.gson.Gson;
import com.utils.serviceusagetracker.model.UsageTrackingMessage;
import com.utils.serviceusagetracker.model.entity.ServiceUsage;
import com.utils.serviceusagetracker.repository.ServiceUsageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class KafkaConsumer {
    private static final String TOPIC = "service-usage-tracker-feed";

    @Autowired
    private ServiceUsageRepository repository;

    @KafkaListener(topics = TOPIC, groupId = "usage-tracker-consumers")
    public void consume(String message) {
        Gson gson = new Gson();
        log.info("Received message: {}", message);
        UsageTrackingMessage utm = gson.fromJson(message, UsageTrackingMessage.class);
        ServiceUsage serviceUsage = new ServiceUsage();
        serviceUsage.setClientId(utm.getClientId());
        serviceUsage.setEventId(utm.getEventId());
        serviceUsage.setEventDate(new Date());
        repository.save(serviceUsage);
    }
}