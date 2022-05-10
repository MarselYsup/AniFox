package ru.itis.anifox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "anime")
public class AnimeEntity extends BaseEntity{
    @Column(nullable = false)
    private String title;

    @Column(name = "year_of_creation")
    private Integer yearOfCreation;

    @Column(name = "count_of_episodes")
    private Integer countOfEpisodes;

    @Column(columnDefinition = "text")
    private String description;

    private Integer rating;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private FileInfoEntity avatar;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioEntity studio;

    @ManyToMany
    @JoinTable(name = "anime_genre", joinColumns = @JoinColumn(name = "anime_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<GenreEntity> genreSet;

    @OneToMany(mappedBy = "animeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SelectedAnimeEntity> selectedAnimeEntityList;
}
