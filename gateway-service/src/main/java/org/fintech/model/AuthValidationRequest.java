package org.fintech.model;

import jakarta.validation.constraints.NotNull;

public record AuthValidationRequest(

        @NotNull
        String jwtToken
) {}
