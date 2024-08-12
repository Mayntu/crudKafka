package test.group.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.group.crud.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
