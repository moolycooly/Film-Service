package org.fintech.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "crew")
public class CrewEntity implements SequenceAware {

    @Transient
    private final String sequenceName = "crew_sequence";

    @Id
    private Long id;

    private String tmdbCreditId;

    private Integer gender;

    private Boolean adult;

    private String name;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("profile_path")
    private String profilePath;

    private String department;

    private String job;


}
