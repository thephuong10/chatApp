package com.chat.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.entities.UserEntity;
import com.chat.app.repositories.IUserRepository;
import com.chat.app.services.IUserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    IUserRepository iUserRepository;

    @GetMapping("friends/{userId}")
    public ResponseEntity<?> getFriendsByUserId(@PathVariable String userId) {

        return ResponseEntity.ok(iUserService.findFriendsByUserId(userId));
    }

    @GetMapping("info/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {

        UserEntity user = iUserService.findOneById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(400).body("Account does not exist");
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestParam(name = "email") String name,
            @RequestParam(name = "password") String password) {

        UserEntity user = iUserService.login(name, password);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(400).body("Account does not exist");
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserEntity userEntity) {

        UserEntity user = iUserService.save(userEntity);

        if (user != null) {
            return ResponseEntity.ok("Signup success");
        }

        return ResponseEntity.status(400).body("Account already exists");
    }

}
