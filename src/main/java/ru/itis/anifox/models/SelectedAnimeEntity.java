package ru.itis.anifox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.anifox.models.enums.Status;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "selected_anime")
public class SelectedAnimeEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private AnimeEntity animeEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
