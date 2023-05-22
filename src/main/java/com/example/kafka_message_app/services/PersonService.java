package com.example.kafka_message_app.services;

import com.example.kafka_message_app.dto.PersonDTO;
import com.example.kafka_message_app.models.Person;
import com.example.kafka_message_app.repository.PersonRepo;
import com.example.kafka_message_app.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepo personRepo;

    @Autowired
    JwtToken jwtUtil;

    @Autowired
    public PersonService(PersonRepo personRepo){
        this.personRepo = personRepo;
    }

    public String insertPerson(PersonDTO personDTO) {
        Person person = personDTO.toPerson();
        if(personDTO.getPassword().isEmpty()){
            person.setGroup(true);
        }
        Optional<Person> optionalPerson = personRepo.findByName(person.getName());
        if(optionalPerson.isPresent()){
            person.setId(optionalPerson.get().getId());
        }else {
            person = personRepo.save(person);
            LOGGER.debug("Person with id {} was inserted in db", person.getId());

        }
        return person.getId();
    }

    public String loginPerson(PersonDTO personDTO) {
        if (!personDTO.isGroup()) {
            Optional<Person> p = personRepo.findByNameAndPassword(personDTO.getName(), personDTO.getPassword());
            if (!p.isPresent()) {
                return jwtUtil.errorAccesTokenGenerator("NOTFOUND");
            }
            return jwtUtil.accesTokenGenerator(p.get());
        }
        else {
            return jwtUtil.errorAccesTokenGenerator("NOTFOUND");
        }
    }


    public List<Person> findAllUserExceptOne(String username) {
        List<Person> personDTOS = personRepo.findAll();
        List<Person> newPersons = new ArrayList<>();
        for (int i = 0; i < personDTOS.size(); i++) {
            if (! personDTOS.get(i).getName().equals(username)){
                newPersons.add(personDTOS.get(i));
            }
        }
        return newPersons;
    }
}
