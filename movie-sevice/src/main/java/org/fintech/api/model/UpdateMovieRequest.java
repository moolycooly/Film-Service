package org.fintech.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Запрос на обновление информации о фильме")
public class UpdateMovieRequest {

    @Size(min = 1, max = 100)
    @Schema(description = "Название фильма", example = "Интерстеллар", required = true)
    private String title;

    @Schema(description = "Фильм для взрослых (если true)", example = "false")
    private Boolean adult;

    @Schema(description = "Бюджет фильма", example = "165000000")
    private Long budget;

    @Schema(description = "Путь к изображению для фона фильма", example = "/images/backdrop.jpg")
    private String backdropPath;

    @Schema(description = "Страны происхождения фильма", example = "[\"США\", \"Великобритания\"]")
    private List<String> originCountry;

    @Schema(description = "Оригинальный язык фильма", example = "en")
    private String originalLanguage;

    @Schema(description = "Оригинальное название фильма", example = "Interstellar")
    private String originalTitle;

    @Size(min = 1, max = 100)
    @Schema(description = "Описание фильма", example = "Группа исследователей отправляется в космос...")
    private String overview;

    @Schema(description = "Путь к изображению для постера фильма", example = "/images/poster.jpg")
    private String posterPath;

    @Schema(description = "Дата релиза фильма", example = "2014-11-07")
    private LocalDate releaseDate;

    @Schema(description = "Доход фильма", example = "677000000")
    private Long revenue;

    @Schema(description = "Продолжительность фильма в минутах", example = "169")
    private Integer runtime;

    @Schema(description = "Статус фильма", example = "Released")
    private String status;

    @Schema(description = "Теглайн фильма", example = "Mankind was born on Earth. It was never meant to die here.")
    private String tagline;

    @Schema(description = "Фильм с видео-контентом (если true)", example = "false")
    private Boolean video;

    @Schema(description = "Ключевые слова, связанные с фильмом", example = "[\"космос\", \"путешествие\", \"выживание\"]")
    private List<String> keywords;

    @Schema(description = "Жанры фильма", example = "[\"драма\", \"научная фантастика\"]")
    private List<String> genres;

}