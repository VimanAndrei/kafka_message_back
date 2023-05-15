package com.example.kafka_message_app.repository;

import com.example.kafka_message_app.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonRepo extends MongoRepository<Person, String> {
    Optional<Person> findByNameAndPassword(String name, String password);

    Optional<Person> findByName(String name);
}
