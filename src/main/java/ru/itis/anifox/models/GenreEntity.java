package ru.itis.anifox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity{

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "genreSet")
    private Set<AnimeEntity> animeSet;

}
