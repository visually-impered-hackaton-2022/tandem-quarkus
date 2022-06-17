package ch.redhat.hackathon.rest;

import ch.redhat.hackathon.domain.Person;
import ch.redhat.hackathon.persistence.PersonRepository;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@Path("persons")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PersonResource {

    @Inject
    PersonRepository personRepository;
    private static final Logger LOGGER = Logger.getLogger(PersonResource.class.getName());

    @GET
    public List<Person> get() {
        LOGGER.info("get all persons from database sorted by id");
        return personRepository.listAll(Sort.by("id"));
    }

    @GET
    @Path("{id}/participants")
    public Person getSingle(Long id) {
        return personRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Person person) {
        if (person == null || person.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        personRepository.persist(person);
        return Response.ok(person).status(CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(Long id, Person person) {
        if (person == null || id == null) {
            throw new WebApplicationException("Fruit name was not set on request.", 422);
        }
        Person entity = personRepository.find("id",id).firstResult();
        if (entity ==null ){
            return Response.ok().status(NOT_FOUND).build();
        }

        personRepository.persist(person);
            return  Response.ok(person).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Long id) {
        return personRepository.deleteById(id)
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build();
    }


}