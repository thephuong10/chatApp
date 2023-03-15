package com.chat.app.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.app.entities.ConversationEntity;
import com.chat.app.repositories.IConversationRepository;
import com.chat.app.services.IConversationService;

@Service
public class ConversationService implements IConversationService {

    @Autowired
    private IConversationRepository iConversationRepository;

    @Override
    public String everTalked(String userId_1, String userId_2) {

        return Optional
                .ofNullable(iConversationRepository.everTalked(userId_1, userId_2))
                .map(c -> c.getId())
                .orElse(null);

    }

    @Override
    public ConversationEntity save(ConversationEntity entity) {
        return iConversationRepository.save(entity);
    }

}
