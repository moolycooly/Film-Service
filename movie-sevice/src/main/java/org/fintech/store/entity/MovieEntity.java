package org.fintech.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Data
@EqualsAndHashCode(exclude = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name ="tmdb_id")
    private Integer tmdbId;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity")
    private Double popularity;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "title")
    private String title;

    @Column(name = "video")
    private Boolean video;

    @Column(name = "vote_average")
    private Double voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres;

}
