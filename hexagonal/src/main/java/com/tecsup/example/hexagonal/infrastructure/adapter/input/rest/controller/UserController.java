package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;


import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        try {

            if (request == null) {
                log.warn("Received null UserRequest");
                return ResponseEntity.badRequest().build();
            }

            log.info("UserRequest: {}", request);
            log.info("Creating request with name: {} and email: {}", request.getName(), request.getEmail());
            User newUser = this.userMapper.toDomain(request);
            log.info("Mapped User entity: {}", newUser);
            User createUser = this.userService.create(newUser);
            UserResponse response = this.userMapper.toResponse(createUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (InvalidUserDataException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        try {

            User user = this.userService.findUser(id);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.warn("User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", id, e);
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/LastName/{lastName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserByLastName(@PathVariable String lastName) {
        try {

            User user = this.userService.findUserBylastName(lastName);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.warn("User not found with LastName: {}", lastName);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with LastName: {}", lastName, e);
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/Document/{dni}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserByDNI(@PathVariable Integer dni) {
        try {

            User user = this.userService.findUserBydocumentNumber(dni);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.warn("User not found with DNI: {}", dni);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with DNI: {}", dni, e);
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping
    @PreAuthorize("hasRole('MONITOR')")
    public ResponseEntity<List<UserResponse>> getUsersByAge(@RequestParam("age") Integer age) {
        try {
            List<User> userList = userService.findUsersByAge(age);
            log.info("Lista de usuarios {}", userList);
            List<UserResponse> userResponseList = userList
                    .stream()
                    .map(this.userMapper::toResponse)
                    .toList();
            log.info("Lista de usuarios response {}", userResponseList);

            return ResponseEntity.ok(userResponseList);
        } catch (Exception e) {
            log.error("Error fetching users with age lower than: {}", age, e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
