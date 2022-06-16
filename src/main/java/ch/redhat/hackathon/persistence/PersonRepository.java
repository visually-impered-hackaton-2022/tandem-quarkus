package ch.redhat.hackathon.persistence;

import javax.enterprise.context.ApplicationScoped;

import ch.redhat.hackathon.domain.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

}
