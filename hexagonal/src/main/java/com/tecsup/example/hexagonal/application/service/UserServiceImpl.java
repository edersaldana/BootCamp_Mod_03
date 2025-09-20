package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User create(User user) {

        // Guardar el usuario usado en el repositorio
        return this.userRepository.save(user);
    }

    @Override
    public User findUser(Long id) {
        return null;
    }
}
