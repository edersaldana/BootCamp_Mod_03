package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper;


import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Convertir User domaint to entity
     * @param domain
     * @return
     */
    UserEntity toEntity(User domain);

    /**
     * Convertir UserENtity to user domain
     * @param entity
     * @return
     */
    User toDomain(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "lastName", source = "lastName")
    User toDomain(UserRequest request);


    UserResponse toResponse(User createUser);
}
