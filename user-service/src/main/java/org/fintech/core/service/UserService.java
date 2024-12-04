package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.*;
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

    public UserDto createUser(CreateUserRequest createUserRequest) {
        checkExistsByEmail(createUserRequest.getEmail());
        checkExistsUsername(createUserRequest.getUsername());

        UserEntity userEntity = userMapper.toEntity(createUserRequest);

        RoleEntity roleEntity = roleRepository.findByName(Authority.USER.name())
                .orElseThrow(()->new ServiceException(ErrorCode.SERVICE_UNAVAILABLE, "Ошибка при регистрации"));
        userEntity.getRoles().add(roleEntity);

        String hashedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        userEntity.setPassword(hashedPassword);

        ProfileEntity profileEntity = ProfileEntity.builder()
                .user(userEntity)
                .build();
        userEntity.setProfile(profileEntity);

        UserEntity user = userRepository.save(userEntity);
        return userMapper.toDto(user);
    }

    public void updateUser(long id, UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = findUserEntityById(id);
        if (updateUserRequest.getEmail() != null) {
            checkExistsByEmail(updateUserRequest.getEmail());
            userEntity.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getPassword() != null) {
            String hashedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
            userEntity.setPassword(hashedPassword);
        }
        userRepository.save(userEntity);
    }

    public UserDto validateUser(LoginUserRequest loginUserRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow(
                        ()->new ServiceException(
                                ErrorCode.NOT_FOUND,
                                String.format("User with username \"%s\" not found", loginUserRequest.getUsername())
                        )
                );
        if (userEntity.getActivate() && validatePassword(userEntity, loginUserRequest.getPassword()))  {
            return userMapper.toDto(userEntity);
        }
        throw new ServiceException(ErrorCode.AUTHENTICATION_FAILED, "Неверный пароль или пользователь неактивирован");
    }

    public void activateUser(ActivateUserRequest activateUserRequest) {
        UserEntity userEntity = findUserEntityByEmail(activateUserRequest.getEmail());
        userEntity.setActivate(true);
        userRepository.save(userEntity);
    }

    private UserEntity findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()->new ServiceException(ErrorCode.NOT_FOUND, "Пользователь с таким email не существует")
        );
    }

    private void checkExistsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(ErrorCode.EMAIL_ALREADY_USED, "Пользователь с таким email уже существует.");
        }
    }

    private void checkExistsUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ServiceException(ErrorCode.USERNAME_ALREADY_USED, "Пользователь с таким именем уже существует.");
        }
    }

    private boolean validatePassword(UserEntity user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    private UserEntity findUserEntityById(long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        ()->new ServiceException(ErrorCode.NOT_FOUND,String.format("User with id \"%d\" not found", id))
                );
    }

}
