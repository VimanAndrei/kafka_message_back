package com.example.kafka_message_app.controllers;

import com.example.kafka_message_app.dto.PersonDTO;
import com.example.kafka_message_app.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/register")
public class RegisterIndex {

    private final PersonService personService;

    @Autowired
    public RegisterIndex(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<?> insertProsumer(@RequestBody PersonDTO personDTO) {
        String personID = personService.insertPerson(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }
}
