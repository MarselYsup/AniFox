package ru.itis.anifox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnimeDto {
    private Long id;

    private String avatar;

    private Integer rating;

    private String title;

    private String description;

    private Integer countOfEpisodes;

    private Integer yearOfCreation;

    private String youtubeUrl;

    private StudioDto studio;

    private List<GenreDto> genres;
}
