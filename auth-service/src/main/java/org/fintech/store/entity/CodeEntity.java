package org.fintech.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="registration_code")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    private String code;

    @Column(name="expire_date")
    private LocalDateTime expireDate;

}
