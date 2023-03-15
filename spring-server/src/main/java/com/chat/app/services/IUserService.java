package com.chat.app.services;

import java.util.List;

import com.chat.app.entities.UserEntity;

public interface IUserService {

    UserEntity save(UserEntity userEntity);

    List<UserEntity> findFriendsByUserId(String id);

    UserEntity findOneById(String id);

    UserEntity login(String email, String password);
}
