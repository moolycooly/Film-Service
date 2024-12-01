package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.CreateUserRequest;
import org.fintech.api.model.UpdateUserRequest;
import org.fintech.core.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для управления пользователями")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping(ApiPaths.USER)
    public void createStudent(@RequestBody @Valid  CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }

    @RequestMapping(value = ApiPaths.USER, method = {RequestMethod.PATCH,RequestMethod.PUT})
    public void updateStudent(
            @PathVariable("id") long id,
            @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        userService.updateUser(id, updateUserRequest);
    }

}