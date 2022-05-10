package ru.itis.anifox.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddAnimeDto {
    private String title;

    private String description;

    private Integer countOfEpisodes;

    private Integer yearOfCreation;

    private String youtubeUrl;

    private String studioTitle;

    private List<GenreDto> genres;
}
