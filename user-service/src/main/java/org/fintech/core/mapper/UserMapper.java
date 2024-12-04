package org.fintech.core.mapper;

import org.fintech.api.model.CreateUserRequest;
import org.fintech.api.model.UserDto;
import org.fintech.store.entity.RoleEntity;
import org.fintech.store.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserMapper {

    public UserEntity toEntity(CreateUserRequest userRequest) {
        return UserEntity.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .roles(new HashSet<>())
                .activate(false)
                .build();
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .roles(userEntity.getRoles().stream().map(RoleEntity::getName).toList())
                .build();
    }

}
