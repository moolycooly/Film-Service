package org.fintech.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.fintech.api.ApiPaths;
import org.fintech.api.model.PatchProfileRequest;
import org.fintech.core.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для управления профилями")
public class ProfileController {

    private final ProfileService profileService;

    @RequestMapping(value = ApiPaths.PROFILE_BY_ID, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public void updateProfile(@PathVariable("id") long id, PatchProfileRequest profile) {
        profileService.patchProfile(id, profile);
    }
}
