package com.example.kafka_message_app.dto;

import com.example.kafka_message_app.models.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private String name;
    private String password;

    public Person toPerson(){
        return new Person(null , name, password);
    }
}
