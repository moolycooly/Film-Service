package org.fintech.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Профиль пользователя")
public class ProfileDto {

    @Schema(description = "Имя пользователя", example = "Александр")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Волков")
    private String lastName;

    @Schema(description = "Пол пользователя", example = "муж", allowableValues = {"муж", "жен"})
    private String gender;

    @Schema(description = "Дата рождения пользователя в формате yyyy-MM-dd", example = "1990-05-20")
    private LocalDate birthDate;

    @Schema(description = "Страна проживания пользователя", example = "Россия")
    private String country;

    @Schema(description = "Город проживания пользователя", example = "Москва")
    private String city;

    @Schema(description = "Telegram-аккаунт пользователя", example = "@alexander_volkov")
    private String telegram;

    @Schema(description = "Описание профиля пользователя", example = "Люблю программировать и путешествовать.")
    private String aboutMe;

    @Schema(description = "Идентификатор аватара", example = "avatar123")
    private String avatarId;
}
