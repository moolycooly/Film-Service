package org.fintech.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credits {

    private List<Cast> cast;

    private List<Crew> crew;
}
