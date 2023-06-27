package com.demoMS.user.service.UserService.services;

import com.demoMS.core.responses.UserResponse;
import com.demoMS.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    // operate Business logic for this User MicroService

    UserResponse createUser(User user);

    // get All Users
    List<UserResponse> getAllUser();

    // Get user by user ID
    UserResponse getUser(String userID);

    // Delete User
}
