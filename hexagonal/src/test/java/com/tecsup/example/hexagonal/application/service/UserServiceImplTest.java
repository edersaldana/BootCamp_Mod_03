package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        // Aquí puedes inicializar los mocks y el servicio
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser() {

        Long ID = 100L;
        String NAME = "Joel";
        String EMAIL = "joel@gmail.com";
        String LASTNAME = "Saldana";

        User newUser = new User(null, NAME, EMAIL, LASTNAME);
        User saveUser = new User(ID, NAME, EMAIL, LASTNAME);

        when(userRepository.save(newUser)).thenReturn(saveUser);

        User realUser = userService.create(newUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());

    }

    @Test
    void findUser() {
        Long ID = 100L;
        String NAME = "Eder";
        String EMAIL = "eder@gmail.com";
        String LASTNAME = "Saldana";

        // Initial Condition
        User existingUser = new User(ID, NAME, EMAIL, LASTNAME);

        // Mocking the repository behavior
        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));

        // Execute the service method
        User realUser = userService.findUser(100L);

        // Validate the results
        assertNotNull(realUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());

    }

    @Test
    public void findUser_NotFound() {
        Long ID_UNKNOW = 999L;

        // Mocking the repository behavior to return empty
        when(userRepository.findById(ID_UNKNOW)).thenReturn(Optional.empty());

        // Execute the service method and expect an exception
        assertThrows(UserNotFoundException.class,
                () -> userService.findUser(ID_UNKNOW));

    }

    @Test
    void findUserByLastName() {
        Long ID = 100L;
        String NAME = "Eder";
        String EMAIL = "eder@gmail.com";
        String LASTNAME = "Saldana";

        // Initial Condition
        User existingUser = new User(ID, NAME, EMAIL, LASTNAME);

        // Mocking the repository behavior
        when(userRepository.findBylastName(LASTNAME)).thenReturn(Optional.of(existingUser));

        // Execute the service method
        User realUser = userService.findUserByLastName(LASTNAME);

        // Validate the results
        assertNotNull(realUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(LASTNAME, realUser.getLastname());
        assertEquals(EMAIL, realUser.getEmail());

    }

}













