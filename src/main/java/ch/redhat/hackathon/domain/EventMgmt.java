package ch.redhat.hackathon.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventMgmt{
    @Id
    @GeneratedValue
    public Long id;
    public String EventName;

}
