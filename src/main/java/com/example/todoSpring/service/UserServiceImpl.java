package com.example.todoSpring.service;

import com.example.todoSpring.entity.User;
import com.example.todoSpring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {

        return userRepository.findByEmail(username);

    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
