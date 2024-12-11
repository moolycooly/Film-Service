package org.fintech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateMovieRequest {

    @NotBlank
    private String title;

    @NotNull
    private Boolean adult;

    private Long budget;

    private String backdropPath;

    private List<String> originCountry;

    private String originalLanguage;

    private String originalTitle;

    @NotBlank
    private String overview;

    private String posterPath;

    private LocalDate releaseDate;

    private Long revenue;

    private Integer runtime;

    private String status;

    private String tagline;

    private Boolean video;

    private Double tmdbVoteAverage;

    private List<String> keywords;

    private List<String> genres;


}
