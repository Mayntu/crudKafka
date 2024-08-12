package test.group.crud.services;

import org.springframework.stereotype.Service;
import test.group.crud.customExceptions.PersonNotFoundException;
import test.group.crud.dto.PersonCreateRequest;
import test.group.crud.dto.PersonUpdateRequest;
import test.group.crud.entities.Person;
import test.group.crud.repositories.PersonRepository;

@Service
public class PersonService
{
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(PersonCreateRequest personCreateRequest) {
        Person person = new Person(personCreateRequest.getName(), personCreateRequest.getSurname(), personCreateRequest.getAge(), personCreateRequest.getGender());
        return personRepository.save(person);
    }

    public Person get(Long id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
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
