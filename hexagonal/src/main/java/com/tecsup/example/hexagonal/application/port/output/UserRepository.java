package com.tecsup.example.hexagonal.application.port.output;

import com.tecsup.example.hexagonal.domain.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findBylastName(String lastname);

    Optional<User> findByEmail(String email);

    Optional<List<User>> findByAge(Integer age);

    Optional<User> findBydocumentNumber(Integer dni);

}
