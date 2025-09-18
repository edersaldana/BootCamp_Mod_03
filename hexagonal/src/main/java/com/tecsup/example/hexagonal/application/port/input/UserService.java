package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;

public interface UserService {

    User create(User user);

    User findUser(Long id);
}
