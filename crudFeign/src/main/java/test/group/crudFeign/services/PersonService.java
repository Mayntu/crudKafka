package test.group.crudFeign.services;

import org.springframework.stereotype.Service;
import test.group.crudFeign.customExceptions.PersonNotFoundException;
import test.group.crudFeign.dto.PersonCreateRequest;
import test.group.crudFeign.dto.PersonUpdateRequest;
import test.group.crudFeign.dto.Person;
import test.group.crudFeign.clients.CrudServiceClient;

@Service
public class PersonService
{
    private final CrudServiceClient personRepository;

    public PersonService(CrudServiceClient personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(PersonCreateRequest personCreateRequest) {
        return personRepository.createPerson(personCreateRequest);
    }

    public Person get(Long id) {
        personRepository.getPersonById(id);
    }

    public Person update(PersonUpdateRequest personUpdateRequest, Long id) {
        Person person = get(id);
        if (personUpdateRequest.getName() != null) {
            person.setName(personUpdateRequest.getName());
        }

        if (personUpdateRequest.getGender() != null) {
            person.setGender(personUpdateRequest.getGender());
        }
        return personRepository.save(person);
    }

    public void delete(Long id) {
        Person person = get(id);
        personRepository.delete(person);
    }
}
