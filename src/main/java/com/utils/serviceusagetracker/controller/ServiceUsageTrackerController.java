package com.utils.serviceusagetracker.controller;

import com.utils.serviceusagetracker.model.ServiceUsageCountsReportDTO;
import com.utils.serviceusagetracker.model.ServiceUsageCountsDTO;
import com.utils.serviceusagetracker.service.ServiceUsageTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class ServiceUsageTrackerController {

    private ServiceUsageTrackerService service;

    @GetMapping("/api/v1.0/client-usage")
    @ResponseBody
    public ResponseEntity<List<ServiceUsageCountsDTO>> getClientUsageByDate(@RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(service.getClientUsageByDate(date));
    }

    @GetMapping("/api/v1.0/client-usage-report")
    @ResponseBody
    public ResponseEntity<List<ServiceUsageCountsReportDTO>> getClientUsageReport(@RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<ServiceUsageCountsReportDTO> reportDate = service.getClientUsageReportByDate(date);
        service.printXMLReport(reportDate);
        return ResponseEntity.ok(reportDate);
    }
}
