package org.fintech.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "keyword")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordEntity implements SequenceAware {

    @Transient
    private final String sequenceName = "keyword_sequence";

    @Id
    private Long id;

    public Integer tmdbId;

    public String name;
}
