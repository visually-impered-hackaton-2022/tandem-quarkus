package ch.redhat.hackathon.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person{
    @Id
    @GeneratedValue
    public Long id;
        public String username;
        public String password;
        public boolean blind;
        String[] interests;
}
