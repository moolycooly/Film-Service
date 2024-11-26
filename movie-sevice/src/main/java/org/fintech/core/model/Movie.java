package org.fintech.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JsonProperty("id")
    private Long id;

    private String title;

    private Boolean adult;

    private Long budget;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    private List<Genre> genres;

    @JsonProperty("origin_country")
    private List<String> originCountry;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_title")
    private String originalTitle;

    private String overview;

    @JsonProperty("popularity")
    private Double tmdbPopularity;

    @JsonProperty("poster_path")
    private String tmdbPosterPath;

    @JsonProperty("production_companies")
    private List<Company> productCompanies;

    @JsonProperty("production_countries")
    private List<Country> productionCountries;

    @JsonProperty("release_date")
    private LocalDate releaseDate;

    private Long revenue;

    private Integer runtime;

    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages;

    private String status;

    private String tagline;

    private Boolean video;

    @JsonProperty("vote_average")
    private Double tmdbVoteAverage;

    @JsonProperty("vote_count")
    private Double tmdbVoteCount;

    private KeywordsResponse keywords;

    private Credits credits;

    @JsonProperty("belongs_to_collection")
    private BelongToCollection belongsToCollection;


}
