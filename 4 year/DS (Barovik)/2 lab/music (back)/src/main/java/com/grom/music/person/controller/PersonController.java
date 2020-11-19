package com.grom.music.person.controller;
import com.grom.music.person.model.CreatePersonDto;
import com.grom.music.person.model.Person;
import com.grom.music.person.model.UpdatePersonDto;
import com.grom.music.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> allPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("persons/{id}")
    public Person getPerson(@PathVariable long id) {
        return personService.getPerson(id);
    }

    @DeleteMapping("persons/{id}")
    public void deletePerson(@PathVariable long id) {
        personService.delete(id);
    }

    @PostMapping("persons")
    public Person createPerson(@RequestBody CreatePersonDto dto) {
        return personService.create(dto);
    }

    @PutMapping("persons")
    public void updatePerson(@RequestBody UpdatePersonDto dto) {
        personService.update(dto);
    }
}
