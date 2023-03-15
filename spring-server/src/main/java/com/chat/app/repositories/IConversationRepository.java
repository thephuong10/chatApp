package com.chat.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chat.app.entities.ConversationEntity;

public interface IConversationRepository extends MongoRepository<ConversationEntity, String> {

    @Query(value = "{ members : { $all : [ :#{#userId_1} , :#{#userId_2} ] } }")
    ConversationEntity everTalked(@Param("userId_1") String userId_1, @Param("userId_2") String userId_2);

}
