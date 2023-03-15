package com.chat.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.entities.MessageEntity;
import com.chat.app.services.IMessageService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody MessageEntity messageEntity) {

        try {

            return ResponseEntity.ok(iMessageService.save(messageEntity));

        } catch (Exception e) {

            return ResponseEntity.status(500).body("Server error");
        }

    }

    @GetMapping("conversation/{conversationId}")
    public ResponseEntity<?> getMessageByConversationId(@PathVariable(name = "conversationId") String conversationId) {

        return ResponseEntity.ok(iMessageService.findAllByConversationId(conversationId));

    }
}
