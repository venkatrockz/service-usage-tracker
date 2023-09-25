package com.utils.serviceusagetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUsageCounts {

    private String clientId;
    private String eventId;
    private Date eventDate;
    private int usageCount;
}
