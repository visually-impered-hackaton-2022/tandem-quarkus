package ch.redhat.hackathon.persistence;

import ch.redhat.hackathon.domain.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {

}
