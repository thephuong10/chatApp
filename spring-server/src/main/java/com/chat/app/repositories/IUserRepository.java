package com.chat.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chat.app.entities.UserEntity;

public interface IUserRepository extends MongoRepository<UserEntity, String> {

    boolean existsByEmail(String email);

    @Query(value = "{ friends: {$in: [ :#{#userId} ] } }")
    List<UserEntity> findFriendsByUserId(@Param("userId") String userId);

    UserEntity findByEmailAndPassword(String email, String password);
}
