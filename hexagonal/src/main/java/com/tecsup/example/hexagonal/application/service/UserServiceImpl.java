package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.Role;
import com.tecsup.example.hexagonal.domain.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User create(User user) {

        // Validation logic can be added here
        validateUserInput(user);

        if(user.getRole() == null)
            user.setRole(Role.USER);

        // Guardar el usuario usado en el repositorio
        return this.userRepository.save(user);
    }

    @Override
    public User findUser(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        User user = this.userRepository.findById(id)
                .orElseThrow( ()-> new UserNotFoundException(id) );

        return user;
    }

    @Override
    public User findUserBylastName(String lastname) {
        if(lastname == null || lastname.length() == 0)
            throw new IllegalArgumentException("Invalid user lastName");

        User user = this.userRepository.findBylastName(lastname)
                .orElseThrow( ()-> new UserNotFoundException(lastname) );

        return user;
    }

    @Override
    public User findUserBydocumentNumber(Integer dni) {
        if (dni == null || dni <= 0) {
            throw new IllegalArgumentException("Invalid user DNI");
        }

        User user = this.userRepository.findBydocumentNumber(dni)
                .orElseThrow( ()-> new UserNotFoundException(dni) );
        return user;
    }

    @Override
    public List<User> findUsersByAge(Integer age) {
        if(age == null)
            throw new IllegalArgumentException("Invalid user age");

        List<User> user = this.userRepository.findByAge(age)
                .orElseThrow( ()-> new UserNotFoundException(Long.valueOf(age)) );

        return user;
    }

    private void validateUserInput(User newUser) {

        if (!newUser.hasValidName())
            throw new InvalidUserDataException("Invalid name");

        if (!newUser.hasValidEmail())
            throw new InvalidUserDataException("Invalid email");

        if (!newUser.hasValidatelastName())
            throw new InvalidUserDataException("Invalid name");

        if (!newUser.hasValidDocumentNumber()){
            throw new InvalidUserDataException("Invalid documentNumber");
        }

    }
}
