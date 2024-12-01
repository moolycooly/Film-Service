package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Права пользователя")
@RestController
@RequiredArgsConstructor
public class AuthorityController {


//    @Operation(summary = "Установка роли подтвержденному профилю")
//    @PostMapping(ApiPaths.AUTHORITY_BY_ID)
//    public void addProfileAuthority(
//            @Min(1) @PathVariable("id") long id,
//            @Valid @RequestBody @Parameter(description = "Права профиля") ProfileAuthorityRequest profileAuthorityRequest
//    ) {
//    }
//
//    @Operation(summary = "Получение списка прав пользователя по его id")
//    @GetMapping(ApiPaths.AUTHORITY_BY_ID)
//    public ProfileAuthorityResponse getProfileAuthoritiesByUserId(
//            @Min(1) @PathVariable(name = "id") @Parameter(description = "Идентификатор пользователя в бд") long id
//    ) {
//    }
//
//    @Operation(summary = "Удаление прав пользователя")
//    @DeleteMapping(ApiPaths.AUTHORITY_BY_ID)
//    public void removeProfileAuthority(
//            @Min(1) @PathVariable int id,
//            @Valid @RequestBody ProfileAuthorityRequest profileAuthorityRequest
//    ) {
//    }

}
