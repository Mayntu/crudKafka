package test.group.crud.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.group.crud.dto.PersonCreateRequest;
import test.group.crud.dto.PersonUpdateRequest;
import test.group.crud.entities.Person;
import test.group.crud.services.PersonService;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person apiCreate(@Valid @RequestBody PersonCreateRequest personCreateRequest) {
        return personService.create(personCreateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person apiGet(@PathVariable Long id) {
        return personService.get(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person apiUpdate(@Valid @RequestBody PersonUpdateRequest personUpdateRequest, @PathVariable Long id) {
        return personService.update(personUpdateRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> apiDelete(@PathVariable Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}