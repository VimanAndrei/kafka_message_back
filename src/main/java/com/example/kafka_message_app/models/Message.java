package com.example.kafka_message_app.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message{
    private String messageFrom;
    private String messageContent;
    private String messageTimestamp;
}
