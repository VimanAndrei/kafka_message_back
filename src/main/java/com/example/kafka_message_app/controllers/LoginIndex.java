package com.example.kafka_message_app.controllers;

import com.example.kafka_message_app.dto.PersonDTO;
import com.example.kafka_message_app.models.Person;
import com.example.kafka_message_app.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginIndex {
    private final PersonService personService;

    @Autowired
    public LoginIndex(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/userslist")
    public ResponseEntity<?> getAllUserExceptUsername(@RequestBody PersonDTO personDTO){
        List<Person> list =personService.findAllUserExceptOne(personDTO.getName());
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> loginProsumer(@RequestBody PersonDTO loginDTO) {
        String loginPerson = personService.loginPerson(loginDTO);
        return new ResponseEntity<>(loginPerson, HttpStatus.OK);
    }
}
