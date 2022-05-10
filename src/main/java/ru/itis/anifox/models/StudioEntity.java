package ru.itis.anifox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "studios")
public class StudioEntity extends BaseEntity{

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private FileInfoEntity avatar;

    @OneToMany(mappedBy = "studio")
    private List<AnimeEntity> animeList;
}
