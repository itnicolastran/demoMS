package com.demoMS.user.service.UserService.services.impl;

import com.demoMS.core.responses.UserResponse;
import com.demoMS.core.responses.fetch.Rating;
import com.demoMS.user.service.UserService.components.RatingServiceRestConsumer;
import com.demoMS.user.service.UserService.entities.User;
import com.demoMS.user.service.UserService.exceptions.UserServiceException;
import com.demoMS.user.service.UserService.repositories.UserRepository;
import com.demoMS.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository repository;

    private RatingServiceRestConsumer ratingServiceRestConsumer;

    public  UserServiceImpl (UserRepository userRepository, RatingServiceRestConsumer ratingServiceRestConsumer){
        this.repository = userRepository;
        this.ratingServiceRestConsumer = ratingServiceRestConsumer;
    }

    @Override
    public UserResponse createUser(User user) {
        LOGGER.debug("createUser() ::");
        String randomUserID = UUID.randomUUID().toString();
        user.setUserID(randomUserID);
        repository.save(user);
        UserResponse userResp = new UserResponse();
        BeanUtils.copyProperties(user,userResp);
        return userResp;
    }

    @Override
    public List<UserResponse> getAllUser() {
        LOGGER.debug("getAllUser() ::");
        return repository.findAll().stream().map(user -> {
            UserResponse userResp = new UserResponse();
            BeanUtils.copyProperties(user,userResp);
            return userResp;
        }).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#userID", value = "users")
    public UserResponse getUser(String userID) {
        LOGGER.debug("getUser() ::");
        User user =repository.findById(userID).orElseThrow(()
                -> new UserServiceException("User not found ::" + userID));
        //fletch user rating from Rating service via Rest Template.
        UserResponse userResp = new UserResponse();
        BeanUtils.copyProperties(user,userResp);
        try {
            ArrayList<Rating> result = ratingServiceRestConsumer.executeGetMethod(
                    ratingServiceRestConsumer.getGetUserRatingHost(userID),null, ArrayList.class).getBody();
            userResp.setRating(result);
        } catch (RestClientException restClientException) {
            LOGGER.warn("WebAPI Issue :: " + restClientException.getMessage());
        }
        return userResp;
    }
}
