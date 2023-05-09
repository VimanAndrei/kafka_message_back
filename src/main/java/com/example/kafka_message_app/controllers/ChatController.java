package com.example.kafka_message_app.controllers;

import com.example.kafka_message_app.constants.KafkaAppConstants;
import com.example.kafka_message_app.dto.MessageDTO;
import com.example.kafka_message_app.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class ChatController {

    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public ChatController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;

    }

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageDTO.toMessage();
        System.out.println(message);
        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send(KafkaAppConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
