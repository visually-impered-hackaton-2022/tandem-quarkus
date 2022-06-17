package ch.redhat.hackathon.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue
    public Long id;
    public String activity;
    public String eventType;
    public String location;

    @JoinTable(name = "participation", joinColumns = @JoinColumn(name = "applied_id"))
    public ArrayList<Person> applied = new ArrayList<>();

    @JoinTable(name = "participation", joinColumns = @JoinColumn(name = "chosen_id"))
    public ArrayList<Person> chosen = new ArrayList<>();






}
