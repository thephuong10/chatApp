package com.chat.app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.app.entities.UserEntity;
import com.chat.app.repositories.IUserRepository;
import com.chat.app.services.IUserService;

@Service
public class UserService implements IUserService {

    private final String AVATAR_DEFAULT = "https://msf-theeltal.de/wp-content/uploads/2018/04/no-avatar.jpg";

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {

        if (!iUserRepository.existsByEmail(userEntity.getEmail())) {

            userEntity.setProfilePicture(AVATAR_DEFAULT);

            UserEntity u = iUserRepository.save(userEntity);

            u.setPassword("");

            return u;

        }

        return null;
    }

    @Override
    public List<UserEntity> findFriendsByUserId(String id) {

        return Optional
                .ofNullable(iUserRepository.findFriendsByUserId(id))
                .map(users -> {
                    users.stream().forEach(u -> u.setPassword(""));
                    return users;
                }).orElse(null);
    }

    @Override
    public UserEntity findOneById(String id) {

        return Optional
                .ofNullable(iUserRepository.findById(id).get())
                .map(u -> {
                    u.setPassword("");
                    return u;
                }).orElse(null);

    }

    @Override
    public UserEntity login(String email, String password) {

        return Optional
                .ofNullable(iUserRepository.findByEmailAndPassword(email, password))
                .map(u -> {
                    u.setPassword("");
                    return u;
                }).orElse(null);
    }

}
