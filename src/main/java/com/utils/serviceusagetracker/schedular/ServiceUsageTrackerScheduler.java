package com.utils.serviceusagetracker.schedular;

import com.utils.serviceusagetracker.model.ServiceUsageCountsReportDTO;
import com.utils.serviceusagetracker.service.ServiceUsageTrackerService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
public class ServiceUsageTrackerScheduler {

    private final ServiceUsageTrackerService service;

    @Scheduled(cron = "0 0 3 * * *", zone = "EST")
    public void capturePreviousDayDataInDatabase() {
        Date date = new Date();
        service.getClientUsageByDate(date);
    }

    @Scheduled(cron = "0 0 4 1 * *", zone = "EST")
    public void generateMonthlyReport() {
        Date date = new Date();
        List<ServiceUsageCountsReportDTO> report =
                service.getClientUsageReportByDate(date);
        service.printXMLReport(report);
    }
}
