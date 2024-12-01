package org.fintech.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private int id;

    @JsonProperty("logo_path")
    private String logoPath;

    private String name;

    @JsonProperty("origin_country")
    private String originCountry;
}
