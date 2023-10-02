package com.example.todoSpring.service;

import com.example.todoSpring.entity.User;

public interface UserService {

    User getUserByUsername(String username);
    User save(User user);



}
