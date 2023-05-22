package com.example.kafka_message_app.services;

import com.example.kafka_message_app.dto.MessageDTO;
import com.example.kafka_message_app.models.Message;
import com.example.kafka_message_app.models.Person;
import com.example.kafka_message_app.repository.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageServce {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServce.class);

    private final MessageRepo messageRepo;

    @Autowired
    public MessageServce(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public String insertMessage(Message message) {
        message = messageRepo.save(message);
        LOGGER.debug("Person with id {} was inserted in db", message.getId());
        return message.getId();
    }

    public List<Message> findAllMesage(MessageDTO messageDTO){
        boolean isGroup = Boolean.parseBoolean(messageDTO.getMessageContent());
        System.out.println(isGroup);
        List<Message> messages = messageRepo.findAll();
        List<Message> newMessageList = new ArrayList<>();
        if(!isGroup) {
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).getMessageFrom().equals(messageDTO.getMessageFrom()) &&
                        messages.get(i).getMessageTo().equals(messageDTO.getMessageTo())) {
                    newMessageList.add(messages.get(i));

                } else {
                    if (messages.get(i).getMessageFrom().equals(messageDTO.getMessageTo()) &&
                            messages.get(i).getMessageTo().equals(messageDTO.getMessageFrom())) {
                        newMessageList.add(messages.get(i));

                    }
                }
            }
        }
        else{
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).getMessageTo().equals(messageDTO.getMessageTo())) {
                    newMessageList.add(messages.get(i));
                }
            }
        }
        newMessageList.sort(Comparator.comparing(Message::getMessageTimestamp));
        return newMessageList;
    }
}
