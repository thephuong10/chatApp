package com.chat.app.services;

import com.chat.app.entities.ConversationEntity;

public interface IConversationService {

    String everTalked(String userId_1, String userId_2);

    ConversationEntity save(ConversationEntity entity);

}
