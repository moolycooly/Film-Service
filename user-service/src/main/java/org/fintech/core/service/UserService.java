package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.CreateUserRequest;
import org.fintech.api.model.LoginUserRequest;
import org.fintech.api.model.UpdateUserRequest;
import org.fintech.api.model.UserDto;
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
        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new ServiceException(ErrorCode.EMAIL_ALREADY_USED, "Пользователь с таким email уже существует.");
        }
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new ServiceException(ErrorCode.USERNAME_ALREADY_USED, "Пользователь с таким именем уже существует.");
        }
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

    public UserDto validateUser(LoginUserRequest loginUserRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow(
                        ()->new ServiceException(
                                ErrorCode.NOT_FOUND,
                                String.format("User with username \"%s\" not found", loginUserRequest.getUsername())
                        )
                );
        if (validatePassword(userEntity, loginUserRequest.getPassword()))  {
            return userMapper.toDto(userEntity);
        }
        throw new ServiceException(ErrorCode.AUTHENTICATION_FAILED, "Неверный пароль");
    }
    private void checkExistsEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(
                    ErrorCode.EMAIL_ALREADY_USED,String.format("User with email \"%s\" is already used", email)
            );
        }
    }
    private boolean validatePassword(UserEntity user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

}
