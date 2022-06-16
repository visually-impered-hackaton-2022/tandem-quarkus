package ch.redhat.hackathon.rest;
import ch.redhat.hackathon.domain.Person;
import ch.redhat.hackathon.persistence.PersonRepository;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
        return personRepository.listAll(Sort.by("lastname"));
    }

    @GET
    @Path("{id}")
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
        if (entity !=null ){
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