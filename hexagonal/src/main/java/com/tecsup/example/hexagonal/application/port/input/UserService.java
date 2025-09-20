package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User create(User user);

    User findUser(Long id);
}
