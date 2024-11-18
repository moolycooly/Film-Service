package org.fintech.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "movie")
public class MovieIndex {

    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Integer)
    private Integer tmdbId;

    @Field(type = FieldType.Boolean)
    private Boolean adult;

    @Field(type = FieldType.Text)
    private String backdropPath;

    @Field(type = FieldType.Integer)
    private List<Integer> genreIds;

    @Field(type = FieldType.Text)
    private String originalLanguage;

    @Field(type = FieldType.Text)
    private String originalTitle;

    @Field(type = FieldType.Text)
    private String overview;

    @Field(type = FieldType.Double)
    private Double popularity;

    @Field(type = FieldType.Text)
    private String posterPath;

    @Field(type = FieldType.Date)
    private String releaseDate;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Boolean)
    private Boolean video;

    @Field(type = FieldType.Double)
    private Double voteAverage;

    @Field(type = FieldType.Integer)
    private Integer voteCount;

    @Field(type = FieldType.Integer)
    private List<Integer> actorsId;




}