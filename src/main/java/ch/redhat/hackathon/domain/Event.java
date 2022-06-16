package ch.redhat.hackathon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue
    public Long id;
    public String activity;
    public String eventType;
    public String location;
}
