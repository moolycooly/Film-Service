package org.fintech.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на изменение данных профиля")
public class PatchProfileRequest {

    @Schema(description = "Имя пользователя", example = "Александр")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Волков")
    private String lastName;

    @Schema(description = "Пол пользователя", example = "муж", allowableValues = {"муж", "жен"})
    private String gender;

    @Schema(description = "Дата рождения в формате yyyy-MM-dd", example = "1990-05-20")
    private LocalDate birthDate;

    @Schema(description = "Страна проживания", example = "Россия")
    private String country;

    @Schema(description = "Город проживания", example = "Москва")
    private String city;

    @Schema(description = "Telegram-аккаунт пользователя", example = "@alexander_volkov")
    private String telegram;

    @Schema(description = "Описание пользователя", example = "Люблю программировать и путешествовать.")
    private String aboutMe;

}