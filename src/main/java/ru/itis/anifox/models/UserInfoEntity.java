package ru.itis.anifox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.anifox.models.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "user_info")
public class UserInfoEntity extends BaseEntity{

    @Column(unique = true)
    private String username;

    private String city;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_descr",columnDefinition = "text")
    private String profileDescription;

    @OneToOne(mappedBy = "userInfo")
    private UserEntity userEntity;
}
