package org.fintech.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на активацию аккаунта")
@AllArgsConstructor
@NoArgsConstructor
public class ActivateAccountRequest {

    @Email
    @NotNull
    @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
    private String email;

    @NotNull
    @Schema(description = "6-значный код", example = "123456")
    private String code;
}
