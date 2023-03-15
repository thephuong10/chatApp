package com.chat.app.services;

import java.util.List;

import com.chat.app.entities.MessageEntity;

public interface IMessageService {

    MessageEntity save(MessageEntity messageEntity);

    List<MessageEntity> findAllByConversationId(String conversationId);

}
