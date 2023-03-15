package com.chat.app.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.entities.ConversationEntity;
import com.chat.app.services.IConversationService;

@RestController
@RequestMapping("/api/conversation/")
public class ConversationController {

    @Autowired
    private IConversationService iConversationService;

    @GetMapping("everTalked")
    public ResponseEntity<?> everTalked(@RequestParam String userId_1, @RequestParam String userId_2) {

        String conversationId = null;

        conversationId = iConversationService.everTalked(userId_1, userId_2);

        if (conversationId == null) {

            ConversationEntity c = new ConversationEntity();

            c.setMembers(Arrays.asList(userId_1, userId_2));

            c = iConversationService.save(c);

            conversationId = c != null ? c.getId() : null;

        }

        return ResponseEntity.ok(conversationId);

    }

}
