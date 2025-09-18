package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User findUser(Long id) {
        return null;
    }
}
