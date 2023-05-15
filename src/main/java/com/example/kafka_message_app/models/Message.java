package com.example.kafka_message_app.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document("Message")
public class Message{
    private String id;
    private String messageFrom;
    private String messageContent;
    private String messageTo;
    private String messageTimestamp;
}
