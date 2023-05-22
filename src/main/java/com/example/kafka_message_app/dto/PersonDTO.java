package com.example.kafka_message_app.dto;

import com.example.kafka_message_app.models.Person;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDTO {
    private String name;
    private String password;
    private boolean isGroup;

    public Person toPerson(){
        return new Person(null , this.name, this.password, this.isGroup);
    }
}
