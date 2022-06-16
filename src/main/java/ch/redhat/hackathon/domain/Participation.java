package ch.redhat.hackathon.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Participation {
    @Id
    @GeneratedValue
    public long Id;
    // 0 = ok, 1 = refused
    public short status; 


}
