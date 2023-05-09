package com.example.kafka_message_app.dto;

import com.example.kafka_message_app.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {
    private String messageFrom;
    private String messageContent;

    public Message toMessage() {
        Message message = new Message();
        message.setMessageContent(this.messageContent);
        message.setMessageFrom(this.messageFrom);
        message.setMessageTimestamp(LocalDateTime.now().toString());
        return message;
    }
}
