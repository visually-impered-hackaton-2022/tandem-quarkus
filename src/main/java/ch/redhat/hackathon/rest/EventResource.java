package ch.redhat.hackathon.rest;

import ch.redhat.hackathon.domain.Event;
import ch.redhat.hackathon.domain.Person;
import ch.redhat.hackathon.persistence.EventRepository;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@Path("events")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class EventResource {

    @Inject
    EventRepository eventRepository;
    private static final Logger LOGGER = Logger.getLogger(EventResource.class.getName());

    @GET
    public List<Event> get() {
        return eventRepository.listAll(Sort.by("id"));
    }

    @GET
    @Path("{id}/person")
    public List<Person> getAppliedPersons(Long id) {

        if (id == null) {
            throw new WebApplicationException("Id is not provided");

        }

        Event event = eventRepository.find("id", id).firstResult();
        if (event == null) {
            throw new WebApplicationException("Event not found");

        }

        return event.applied;

    }

    @POST
    @Transactional
    public Response create(Event Event) {
        if (Event == null || Event.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        eventRepository.persist(Event);
        return Response.ok(Event).status(CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(Long id, Event Event) {
        if (Event == null || id == null) {
            throw new WebApplicationException("Fruit name was not set on request.", 422);
        }
        Event entity = eventRepository.find("id",id).firstResult();
        if (entity !=null ){
            return Response.ok().status(NOT_FOUND).build();
        }

        eventRepository.persist(Event);
            return  Response.ok(Event).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Long id) {
        return eventRepository.deleteById(id)
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build();
    }


}