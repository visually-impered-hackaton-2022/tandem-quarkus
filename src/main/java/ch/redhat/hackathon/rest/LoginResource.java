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

@Path("login")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class LoginResource {

    @Inject
    PersonRepository personRepository;
    private static final Logger LOGGER = Logger.getLogger(LoginResource.class.getName());


    @POST
    @Transactional
    public Response Login(Person person){
        LOGGER.info("login user: " + person.username + " password: " + person.password);
        Person entity = personRepository.find("FROM Person p  WHERE p.username = ?1 and p.password = ?2",
                    person.username,person.password).firstResult();
        if (entity == null){
            LOGGER.warn("user not found");
            return Response.ok().status(401).build();
        }
        if (person.password.equals(entity.password)){
            LOGGER.info("user logged !");
            return Response.ok("{\"id\": "+entity.id+"}").build();

        }
        return Response.ok().build();
    }




}