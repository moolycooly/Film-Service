package org.fintech.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CastDto {

    private Long id;

    private String tmdbCreditId;

    private Integer gender;

    private Boolean adult;

    private String role;

    private String name;

    private String originalName;

    private Double tmdbPopularity;

    private String profilePath;

    private String character;

}
