package com.example.kafka_message_app.repository;

import com.example.kafka_message_app.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepo extends MongoRepository<Message, String> {

}
