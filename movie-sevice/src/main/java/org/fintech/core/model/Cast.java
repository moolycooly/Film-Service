package org.fintech.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cast {

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

    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    private Integer order;


}
