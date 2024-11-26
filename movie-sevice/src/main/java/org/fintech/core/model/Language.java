package org.fintech.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {

    @JsonProperty("iso_639_1")
    private String id;

    @JsonProperty("english_name")
    private String englishName;

    private String name;
}
