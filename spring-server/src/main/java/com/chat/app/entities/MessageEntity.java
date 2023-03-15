package com.chat.app.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "message")
public class MessageEntity {

    @Id
    private String id;

    private String text;

    private String conversationId;

    private String senderId;

    private Date date;
}
