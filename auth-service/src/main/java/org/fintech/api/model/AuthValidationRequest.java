package org.fintech.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AuthValidationRequest(

        @NotNull
        @Schema(description = "JWT токен", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        String jwtToken
) {}
