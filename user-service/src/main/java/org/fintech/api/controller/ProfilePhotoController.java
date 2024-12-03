package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.facade.ProfilePhotoFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Профили")
@RestController
@RequiredArgsConstructor
public class ProfilePhotoController {

    private final ProfilePhotoFacade profilePhotoFacade;

    @Operation(summary = "Сохранение/обновление фото профиля")
    @PostMapping(value = ApiPaths.PROFILE_BY_ID_PHOTO, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadProfilePhoto(
            @PathVariable("id") long id,
            @RequestParam("file") MultipartFile file
    ) {
        profilePhotoFacade.saveProfilePhoto(id, file);
    }

    @Operation(summary = "Получение фото профиля")
    @DeleteMapping(value = ApiPaths.PROFILE_BY_ID_PHOTO)
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable("id") long id) {
        return profilePhotoFacade.getProfilePhoto(id);
    }

    @Operation(summary = "Удаление фото профиля")
    @DeleteMapping(value = ApiPaths.PROFILE_BY_ID_PHOTO)
    public void uploadProfilePhoto(@PathVariable("id") long id) {
        profilePhotoFacade.deleteProfilePhoto(id);
    }
}
