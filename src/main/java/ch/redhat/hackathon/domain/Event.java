package ch.redhat.hackathon.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Event {
    @Id
    @GeneratedValue
    public Long id;
    public String activity;
    public String eventType;
    public String location;
    @OneToMany
    public List<Person> persons;



}
