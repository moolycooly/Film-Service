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
    private String email;

    @NotNull
    private String code;
}
