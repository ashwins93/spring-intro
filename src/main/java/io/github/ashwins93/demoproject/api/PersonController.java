package io.github.ashwins93.demoproject.api;

import io.github.ashwins93.demoproject.models.Person;
import io.github.ashwins93.demoproject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{personId}")
    public Person getPersonById(@PathVariable("personId") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePersonById(@PathVariable("personId") UUID id) {
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{personId}")
    public void updatePersonById(@PathVariable("personId") UUID id,
                                 @Valid @NotNull @RequestBody Person person) {
        personService.updatePersonById(id, person);
    }
}
