package org.fintech.api.model;

import jakarta.validation.constraints.Email;
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
public class LoginUserRequest {

    @Size(min = 4, max = 50)
    @NotNull
    private String username;

    @NotNull
    @Size(min = 4, max = 50)
    private String password;

}