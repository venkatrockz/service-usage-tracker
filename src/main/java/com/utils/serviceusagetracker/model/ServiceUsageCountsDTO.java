package com.utils.serviceusagetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceUsageCountsDTO {

    private String eventId;
    private String clientId;
    private Long usageCount;
}
