package ch.redhat.hackathon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Activity {
    @Id
    @GeneratedValue
    public Long id;
    private String activityName;

    public String getActivityName() {
        return activityName;
    }

    public void setActivtyNameityName(String activityName) {
         this.activityName=activityName;
    }
}
