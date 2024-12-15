package org.fintech.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на обновление данных пользователя")
public class UpdateUserRequest {

    @Email
    @Schema(description = "Электронная почта пользователя",
            example = "user@example.com",
            required = true)
    private String email;

    @Size(min = 4, max = 50)
    @Schema(description = "Пароль пользователя (от 4 до 50 символов)",
            example = "securePassword123",
            required = true)
    private String password;

}
