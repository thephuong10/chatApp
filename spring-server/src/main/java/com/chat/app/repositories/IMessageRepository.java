package com.chat.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.app.entities.MessageEntity;

public interface IMessageRepository extends MongoRepository<MessageEntity, String> {

    List<MessageEntity> findAllByConversationId(String conversationId);

}
