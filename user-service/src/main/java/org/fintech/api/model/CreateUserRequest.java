package org.fintech.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class CreateUserRequest {

    @NotNull
    @Size(min = 4, max = 50)
    @Schema(description = "Имя пользователя", example = "John")
    private String username;

    @Email
    @NotNull
    @Schema(description = "Email пользователя", example = "example@mail.ru")
    private String email;

    @NotNull
    @Size(min = 4, max = 50)
    @Schema(description = "Пароль пользователя", example = "password")
    private String password;

}
