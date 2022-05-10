package ru.itis.anifox.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.anifox.models.enums.Role;
import ru.itis.anifox.models.enums.State;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "confirm_code", unique = true)
    private String confirmCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    private UserInfoEntity userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private FileInfoEntity avatar;


    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SelectedAnimeEntity> selectedAnimeEntityList;

}
