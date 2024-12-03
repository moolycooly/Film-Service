package org.fintech.api.facade;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.PatchProfileRequest;
import org.fintech.api.model.ProfileDto;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.fintech.core.model.FileType;
import org.fintech.core.service.FileStorageService;
import org.fintech.core.service.ProfileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
@RequiredArgsConstructor
public class ProfilePhotoFacade {

    private final FileStorageService fileStorageService;

    private final ProfileService profileService;

    public void saveProfilePhoto(long userId, MultipartFile file) {
        try {
            String avatarId = fileStorageService.uploadFile(file, FileType.PROFILE_PHOTO);
            PatchProfileRequest patchProfileRequest = new PatchProfileRequest();
            patchProfileRequest.setAvatarId(avatarId);
            profileService.patchProfile(userId, patchProfileRequest);
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, "Не получилось загрузить фото в хранилище");
        }
    }

    public ResponseEntity<byte[]> getProfilePhoto(long userId) {
        ProfileDto profileDto = profileService.getProfile(userId);
        String avatarId = profileDto.getAvatarId();
        String[] parsedAvatarId = avatarId.split("\\.");
        byte[] downloadedFile;
        MediaType mediaType;
        try{
            mediaType = MediaType.valueOf(Files.probeContentType(Path.of("." + parsedAvatarId[2])));
            downloadedFile = fileStorageService.downloadFile(FileType.PROFILE_PHOTO.getBucketName(), parsedAvatarId[1]+parsedAvatarId[2]);
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR, "Не получилось получить фото");
        }
        return ResponseEntity.ok().contentType(mediaType).body(downloadedFile);
    }

    public void deleteProfilePhoto(long userId) {
        ProfileDto profile = profileService.getProfile(userId);
        fileStorageService.deleteFile(profile.getAvatarId());
        profileService.changeAvatar(userId, null);
    }
}
