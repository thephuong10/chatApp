package com.chat.app.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "user")
public class UserEntity {

    @Id
    private String id;

    private String fullName;

    private String email;

    private String password;

    private List<String> friends;

    private String profilePicture;

    // private List<String> conversations;

}
