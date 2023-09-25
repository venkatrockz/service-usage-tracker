package com.utils.serviceusagetracker.repository;

import com.utils.serviceusagetracker.model.ServiceUsageCountsReportDTO;
import com.utils.serviceusagetracker.model.entity.ServiceUsageAggregator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ServiceUsageAggregatorRepository extends CrudRepository<ServiceUsageAggregator, Integer> {

    @Query("SELECT new com.utils.serviceusagetracker.model.ServiceUsageCountsReportDTO(s.clientId, s.eventId, s.usageCount) from SERVICE_USAGE_AGGREGATOR s where " +
            "s.eventDate >= ?1 AND  s.eventDate < ?2")
    List<ServiceUsageCountsReportDTO> getReportData(Date startDate, Date endDate);
}
