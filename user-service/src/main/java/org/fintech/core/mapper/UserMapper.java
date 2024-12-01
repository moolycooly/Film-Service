package org.fintech.core.mapper;

import org.fintech.api.model.CreateUserRequest;
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
                .build();
    }
}
