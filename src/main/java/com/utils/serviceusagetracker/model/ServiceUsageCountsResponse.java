package com.utils.serviceusagetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ServiceUsageCountsResponse {
    List<ServiceUsageCountsReportDTO> report;
}
