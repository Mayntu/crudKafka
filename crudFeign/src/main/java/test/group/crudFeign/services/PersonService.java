package test.group.crudFeign.services;

import org.springframework.stereotype.Service;
import test.group.crudFeign.clients.CrudServiceClient;
import test.group.crudFeign.dto.Person;
import test.group.crudFeign.dto.PersonCreateRequest;
import test.group.crudFeign.dto.PersonUpdateRequest;

@Service
public class PersonService
{
    private final CrudServiceClient personRequest;

    public PersonService(CrudServiceClient personRequest) {
        this.personRequest = personRequest;
    }

    public Person create(PersonCreateRequest personCreateRequest) {
        return personRequest.createPerson(personCreateRequest);
    }

    public Person get(Long id) {
        return personRequest.getPersonById(id);
    }

    public Person update(PersonUpdateRequest personUpdateRequest, Long id) {
        return personRequest.updatePerson(personUpdateRequest);
    }

    public void delete(Long id) {
        personRequest.deletePerson(id);
    }
}
