package com.example.kafka_message_app.consumer;

import com.example.kafka_message_app.constants.KafkaAppConstants;
import com.example.kafka_message_app.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private SimpMessagingTemplate template;

    @Autowired
    public MessageListener(SimpMessagingTemplate template){
        this.template = template;
    }

    @KafkaListener(
            topics = KafkaAppConstants.KAFKA_TOPIC,
            groupId = KafkaAppConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener: " + message);
        template.convertAndSend("/topic/group", message);
    }
}
