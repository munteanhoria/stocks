package com.devm8.stocks.services;

import com.devm8.stocks.entities.User;
import com.devm8.stocks.exceptions.ResourceNotFoundException;
import com.devm8.stocks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found!"));
    }
}
