package com.utils.serviceusagetracker.model;

import lombok.Data;

@Data
public class UsageTrackingMessage {
    private String clientId;
    private String eventId;
}

