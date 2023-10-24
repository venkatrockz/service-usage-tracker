package com.utils.serviceusagetracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.utils.serviceusagetracker.model.ServiceUsageCountsReportDTO;
import com.utils.serviceusagetracker.model.ServiceUsageCountsDTO;
import com.utils.serviceusagetracker.model.entity.ServiceUsageAggregator;
import com.utils.serviceusagetracker.repository.ServiceUsageAggregatorRepository;
import com.utils.serviceusagetracker.repository.ServiceUsageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class ServiceUsageTrackerService {

    private ServiceUsageRepository repository;
    public ServiceUsageAggregatorRepository aggregatorStore;

    public List<ServiceUsageCountsDTO> getClientUsageByDate(Date date) {
        Date nextDate = previousDate(date);
        List<String> clients = repository.getDistinctByClientId(nextDate, date);
        List<ServiceUsageCountsDTO> serviceUsageCounts = new ArrayList<>();
        List<ServiceUsageCountsDTO> response = new ArrayList<>();
        for (String client : clients) {
            serviceUsageCounts = repository.getDistinctByClientId(client, nextDate, date);
            for (ServiceUsageCountsDTO countsDTO : serviceUsageCounts) {
                ServiceUsageAggregator aggregator =
                        new ServiceUsageAggregator();
                aggregator.setClientId(client);
                aggregator.setUsageCount(countsDTO.getUsageCount());
                aggregator.setEventId(countsDTO.getEventId());
                aggregator.setEventDate(date);
                aggregatorStore.save(aggregator);
                response.add(countsDTO);
            }
        }
        return response;
    }

    public List<ServiceUsageCountsReportDTO> getClientUsageReportByDate(Date date) {
        Date prevdate = previousMonth(date);
        List<ServiceUsageCountsReportDTO> serviceUsageCounts
                = aggregatorStore.getReportData(prevdate, date);
        return serviceUsageCounts;
    }

    public void printXMLReport(List<ServiceUsageCountsReportDTO> report) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(report);
            log.info(xml);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Date previousDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    private Date previousMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }
}
