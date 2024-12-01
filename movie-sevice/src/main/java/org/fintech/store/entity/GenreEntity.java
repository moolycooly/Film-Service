package org.fintech.store.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreEntity implements  SequenceAware {

    @Transient
    private final String sequenceName = "genre_sequence";

    @Id
    private Long id;

    private Integer tmdbId;

    private String name;
}
