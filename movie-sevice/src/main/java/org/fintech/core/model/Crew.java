package org.fintech.core.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crew {

    private Long id;

    private Integer gender;

    private Boolean adult;

    @JsonProperty("known_for_department")
    private String role;

    private String name;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("popularity")
    private Double tmdbPopularity;

    @JsonProperty("profile_path")
    private String profilePath;

    @JsonProperty("cast_id")
    private Integer castId;

    @JsonProperty("credit_id")
    private String creditId;

    private String department;

    private String job;


}
