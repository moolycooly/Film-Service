package org.fintech.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrewDto {

    private Long id;

    private String tmdbCreditId;

    private Integer gender;

    private Boolean adult;

    private String name;

    private String originalName;

    private Double tmdbPopularity;

    private String profilePath;

    private String department;

    private String job;
}
