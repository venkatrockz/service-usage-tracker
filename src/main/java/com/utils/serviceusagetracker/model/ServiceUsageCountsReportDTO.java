package com.utils.serviceusagetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceUsageCountsReportDTO {

    private String clientId;
    private String eventId;
    private Long usageCount;
}
