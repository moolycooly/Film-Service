package org.fintech.core.mapper;

import org.fintech.api.model.ProfileDto;
import org.fintech.store.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDto toDto(ProfileEntity profile) {
        return ProfileDto.builder()
                .city(profile.getCity())
                .country(profile.getCountry())
                .aboutMe(profile.getAbout())
                .telegram(profile.getTelegram())
                .avatarId(profile.getAvatarId())
                .birthDate(profile.getBirthDate())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .gender(profile.getGender())
                .build();
    }
}
