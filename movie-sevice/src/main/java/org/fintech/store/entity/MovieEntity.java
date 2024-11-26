package org.fintech.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity implements  SequenceAware {

    @Transient
    private final String sequenceName = "movie_sequence";

    @Id
    private Long id;

    private Long tmdbId;

    private String title;

    private Boolean adult;

    private Long budget;

    private String backdropPath;

    @DBRef
    private List<GenreEntity> genres;

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

    @DBRef(lazy = true)
    private List<KeywordEntity> keywords;

    @DBRef(lazy = true)
    private List<CastEntity> cast;

    @DBRef(lazy = true)
    private List<CrewEntity> crews;



}
