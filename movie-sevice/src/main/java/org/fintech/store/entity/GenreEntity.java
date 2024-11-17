package org.fintech.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="genre")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GenreEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;
}
