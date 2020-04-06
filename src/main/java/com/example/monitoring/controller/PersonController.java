package com.example.monitoring.controller;

import com.example.monitoring.model.Person;
import com.example.monitoring.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private PersonRepository repository;


    PersonController(PersonRepository personRepository) {
        repository = personRepository;
    }

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPerson() {
        var persons = repository.findAll();
        return ResponseEntity.ok(persons);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        var saved = repository.save(person);
        logger.info("Saved " + person.getFirstName());
        return ResponseEntity.ok(saved);
    }
}
