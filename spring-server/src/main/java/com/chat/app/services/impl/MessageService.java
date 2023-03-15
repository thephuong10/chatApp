package com.chat.app.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.chat.app.entities.MessageEntity;
import com.chat.app.repositories.IMessageRepository;
import com.chat.app.services.IMessageService;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository iMessageRepository;

    @Override
    public MessageEntity save(MessageEntity messageEntity) {
        messageEntity.setDate(new Date(System.currentTimeMillis()));
        return iMessageRepository.save(messageEntity);

    }

    @Override
    public List<MessageEntity> findAllByConversationId(String conversationId) {

        return iMessageRepository.findAllByConversationId(conversationId);
    }
}
