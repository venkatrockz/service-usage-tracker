package com.utils.serviceusagetracker.repository;

import com.utils.serviceusagetracker.model.ServiceUsageCountsDTO;
import com.utils.serviceusagetracker.model.entity.ServiceUsage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ServiceUsageRepository extends CrudRepository<ServiceUsage, Integer> {

    @Query("SELECT distinct s.clientId from SERVICE_USAGE s where s.eventDate >= ?1 AND  s.eventDate < ?2")
    List<String> getDistinctByClientId(Date startDate, Date endDate);

    @Query("SELECT new com.utils.serviceusagetracker.model.ServiceUsageCountsDTO(s.eventId, s.clientId, count(s.eventId)) from SERVICE_USAGE s where " +
            "s.clientId=?1 AND s.eventDate >= ?2 AND  s.eventDate < ?3 group by s.eventId")
    List<ServiceUsageCountsDTO> getDistinctByClientId(String clientId, Date startDate, Date endDate);
}
