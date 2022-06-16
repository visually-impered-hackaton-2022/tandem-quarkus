package ch.redhat.hackathon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Participation {
    @Id
    @GeneratedValue
    public Long id;
    // public Person pers;

    public String pers;

    public void addParticipant(String pers){
        this.pers=pers;
    }
}
