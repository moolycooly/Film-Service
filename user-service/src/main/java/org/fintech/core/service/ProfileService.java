package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.api.model.PatchProfileRequest;
import org.fintech.api.model.ProfileDto;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.fintech.core.mapper.ProfileMapper;
import org.fintech.store.entity.ProfileEntity;
import org.fintech.store.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileDto getProfile(long userId) {

        ProfileEntity profileEntity = profileRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.NOT_FOUND, String.format("User with id \"%d\" not found", userId
                )));
        return profileMapper.toDto(profileEntity);
    }

    public void patchProfile(long userId, PatchProfileRequest patchProfileRequest) {
        ProfileEntity profileEntity = profileRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.NOT_FOUND, String.format("User with id \"%d\" not found", userId
                )));
        if (patchProfileRequest.getFirstName() != null) {
            profileEntity.setFirstName(patchProfileRequest.getFirstName());
        }
        if (patchProfileRequest.getLastName() != null) {
            profileEntity.setLastName(patchProfileRequest.getLastName());
        }
        if (patchProfileRequest.getGender() != null) {
            profileEntity.setGender(patchProfileRequest.getGender());
        }
        if (patchProfileRequest.getBirthDate() != null) {
            profileEntity.setBirthDate(patchProfileRequest.getBirthDate());
        }
        if (patchProfileRequest.getCountry() != null) {
            profileEntity.setCountry(patchProfileRequest.getCountry());
        }
        if (patchProfileRequest.getCity() != null) {
            profileEntity.setCity(patchProfileRequest.getCity());
        }
        if (patchProfileRequest.getTelegram() != null) {
            profileEntity.setTelegram(patchProfileRequest.getTelegram());
        }
        if (patchProfileRequest.getAboutMe() != null) {
            profileEntity.setAbout(patchProfileRequest.getAboutMe());
        }
        profileRepository.save(profileEntity);
    }

    public void changeAvatar(long userId, String avatar) {
        ProfileEntity profileEntity = profileRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.NOT_FOUND, String.format("User with id \"%d\" not found", userId
                )));
        profileEntity.setAvatarId(avatar);
        profileRepository.save(profileEntity);
    }

}
