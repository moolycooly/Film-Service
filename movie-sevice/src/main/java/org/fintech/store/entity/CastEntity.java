package org.fintech.store.entity;

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
@Document(collection = "cast")
public class CastEntity implements SequenceAware {

    @Transient
    private final String sequenceName = "cast_sequence";

    @Id
    private Long id;

    private String tmdbCreditId;

    private Integer gender;

    private Boolean adult;

    private String role;

    private String name;

    private String originalName;

    private String profilePath;

    private String character;

}
