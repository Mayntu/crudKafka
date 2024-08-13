package test.group.crudFeign.clients;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import test.group.crudFeign.dto.Person;
import test.group.crudFeign.dto.PersonCreateRequest;
import test.group.crudFeign.dto.PersonUpdateRequest;


@FeignClient(name = "crud-service-client", url = "localhost:8080")
public interface CrudServiceClient {
    @GetMapping("/{id}")
    Person getPersonById(@PathVariable Long id);

    @PostMapping
    Person createPerson(@Valid @RequestBody PersonCreateRequest personCreateRequest);

    @PatchMapping("/{id}")
    Person updatePerson(@Valid @RequestBody PersonUpdateRequest personUpdateRequest);

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable Long id);
}
