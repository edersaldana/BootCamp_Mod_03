package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User create(User user);

    User findUser(Long id);

    User findUserBylastName(String lastname);

    User findUserBydocumentNumber(Integer dni);

    List<User> findUsersByAge(Integer age);
}
