package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.PatchProfileRequest;
import org.fintech.api.model.ProfileDto;
import org.fintech.core.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для управления профилями")
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "Получение профиля по ID",
            description = "Возвращает профиль пользователя с указанным ID")
    @GetMapping(ApiPaths.PROFILE_BY_ID)
    public ResponseEntity<ProfileDto> getProfile(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable("id") long id) {
        return ResponseEntity.ok().body(profileService.getProfile(id));
    }

    @Operation(summary = "Обновление профиля пользователя",
            description = "Обновляет профиль пользователя с указанным ID. " +
                    "Поддерживаются полные (PUT) или частичные (PATCH) изменения.")
    @RequestMapping(value = ApiPaths.PROFILE_BY_ID, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Void> updateProfile(
            @Parameter(description = "ID пользователя", example = "1") @PathVariable("id") long id,
            @RequestBody PatchProfileRequest profile
    ) {
        profileService.patchProfile(id, profile);
        return ResponseEntity.noContent().build();
    }
    }