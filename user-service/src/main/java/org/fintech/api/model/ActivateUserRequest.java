package org.fintech.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на активацию аккаунта")
public class ActivateUserRequest {

    @Email
    @NotNull
    @Schema(description = "Email пользователя", example = "example@mail.ru")
    private String email;
}
