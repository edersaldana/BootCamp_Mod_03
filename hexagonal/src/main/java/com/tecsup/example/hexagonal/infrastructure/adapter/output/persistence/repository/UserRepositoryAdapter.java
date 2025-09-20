package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {

        // convertir: Domain to Entity
        UserEntity userEntity = this.userMapper.toEntity(user);

        // Save Entity
        UserEntity entityCreated = this.jpaRepository.save(userEntity);

        log.info("User created: {}", entityCreated);
        // convertir; Entity to Domain
        return this.userMapper.toDomain(entityCreated);
    }
}
