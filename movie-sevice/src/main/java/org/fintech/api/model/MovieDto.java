package org.fintech.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {

    private Long id;

    private Long tmdbId;

    private String title;

    private Boolean adult;

    private Long budget;

    private String backdropPath;

    private List<GenreDto> genres;

    private List<String> originCountry;

    private String originalLanguage;

    private String originalTitle;

    private String overview;

    private String posterPath;

    private LocalDate releaseDate;

    private Long revenue;

    private Integer runtime;

    private String status;

    private String tagline;

    private Boolean video;

    private Double tmdbVoteAverage;

    private List<KeywordDto> keywords;

    private List<CastDto> cast;

    private List<CrewDto> crews;



}
