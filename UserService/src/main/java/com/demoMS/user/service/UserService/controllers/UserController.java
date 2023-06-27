package com.demoMS.user.service.UserService.controllers;

import com.demoMS.core.responses.UserResponse;
import com.demoMS.user.service.UserService.entities.User;
import com.demoMS.user.service.UserService.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/User")
public class UserController {

    private UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;
    }

    //Create User API
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        UserResponse createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //Get All Users API
    @GetMapping
    public  ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    //Get User by ID API
    @GetMapping("/{userID}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable String userID) {
        UserResponse result = userService.getUser(userID);
        return  ResponseEntity.ok(result);
    }
}
