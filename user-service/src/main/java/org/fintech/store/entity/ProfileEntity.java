package org.fintech.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "avatar_id")
    private String avatarId;

    @Column(name = "gender", length = 15)
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "about", length = 1000)
    private String about;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
