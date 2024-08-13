package test.group.crudFeign.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.group.crudFeign.dto.Person;
import test.group.crudFeign.dto.PersonCreateRequest;
import test.group.crudFeign.dto.PersonUpdateRequest;
import test.group.crudFeign.services.PersonService;

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
    public ResponseEntity<String> apiDelete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok().body("success");
    }
}