package org.fintech.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Права пользователя")
public class TokenValidationResponse {

    @Schema(name = "id", description = "id пользователя")
    private Long id;

    @Schema(name = "authorities", description = "Права принадлежащие пользователю")
    private List<String> authority;

}