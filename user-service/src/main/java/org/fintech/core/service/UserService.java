package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.CreateUserRequest;
import org.fintech.api.model.UpdateUserRequest;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.fintech.core.mapper.UserMapper;
import org.fintech.core.model.Authority;
import org.fintech.store.entity.ProfileEntity;
import org.fintech.store.entity.RoleEntity;
import org.fintech.store.entity.UserEntity;
import org.fintech.store.repository.RoleRepository;
import org.fintech.store.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует.");
        }
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует.");
        }
        UserEntity userEntity = userMapper.toEntity(createUserRequest);

        RoleEntity roleEntity = roleRepository.findByName(Authority.USER.name())
                .orElseThrow(()->new ServiceException(ErrorCode.SERVICE_UNAVAILABLE, "Ошибка при регистрации"));
        userEntity.getRoles().add(roleEntity);

        String hashedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        userEntity.setPassword(hashedPassword);

        ProfileEntity profileEntity = ProfileEntity.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .user(userEntity)
                .build();
        userEntity.setProfile(profileEntity);
        userRepository.save(userEntity);
    }

    public void updateUser(long id, UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(
                        ()->new ServiceException(ErrorCode.NOT_FOUND,String.format("User with id \"%d\" not found", id))
                );
        if (updateUserRequest.getEmail() != null) {
            checkExistsEmail(updateUserRequest.getEmail());
            userEntity.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getPassword() != null) {
            String hashedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
            userEntity.setPassword(hashedPassword);
        }
        userRepository.save(userEntity);
    }

    private void checkExistsEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(
                    ErrorCode.EMAIL_ALREADY_USED,String.format("User with email \"%s\" is already used", email)
            );
        }
    }

}
