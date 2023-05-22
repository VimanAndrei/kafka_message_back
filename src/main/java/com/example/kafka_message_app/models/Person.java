package com.example.kafka_message_app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Person")
public class Person {
    private String id;
    private String name;
    private String password;
    private boolean isGroup;
}
