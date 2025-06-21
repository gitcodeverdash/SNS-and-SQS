package com.yogesh.UserServices.services;

import com.yogesh.UserServices.entities.User;

import java.util.List;

public interface UserService {

    //create user
    public User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);
}
