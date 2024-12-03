package org.fintech.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private String country;

    private String city;

    private String telegram;

    private String aboutMe;

    private String avatarId;
}
