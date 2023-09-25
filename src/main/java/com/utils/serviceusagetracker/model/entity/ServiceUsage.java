package com.utils.serviceusagetracker.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "SERVICE_USAGE")
public class ServiceUsage {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @Column(name = "CLIENT_ID")
    private String clientId;
    @Column(name = "EVENT_ID")
    private String eventId;
    @Column(name = "EVENT_DATE")
    private Date eventDate;
}
