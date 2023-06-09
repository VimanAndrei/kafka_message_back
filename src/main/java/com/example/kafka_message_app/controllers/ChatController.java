package com.example.kafka_message_app.controllers;

import com.example.kafka_message_app.constants.KafkaAppConstants;
import com.example.kafka_message_app.dto.MessageDTO;
import com.example.kafka_message_app.models.Message;
import com.example.kafka_message_app.services.MessageServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ChatController {

    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private MessageServce messageService;

    @Autowired
    public ChatController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;

    }

    @PostMapping("/api/send")
    @CrossOrigin
    public void sendMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageDTO.toMessage();
        System.out.println(message);
        messageService.insertMessage(message);
        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send(KafkaAppConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/getmessagelist")
    @CrossOrigin
    public ResponseEntity<?> getAllMessages(@RequestBody MessageDTO messageDTO){
        List<Message> list =messageService.findAllMesage(messageDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        //Sending this message to all the subscribers
        return message;
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/group")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getMessageFrom());
        return message;
    }
}
