package com.chat.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chat.app.entities.MessageEntity;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message/private")
    public MessageEntity sendMessage(@Payload MessageEntity message) {
        messagingTemplate.convertAndSendToUser(message.getConversationId(), "/private", message);
        return message;
    }

}
