package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository  extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findBylastName(String lastName);

    Optional<UserEntity> findByEmail(String email);

    Optional<List<UserEntity>> findByAgeLessThan(Integer age);

    Optional<UserEntity> findBydocumentNumber(Integer dni);
}
