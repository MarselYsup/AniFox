package ru.itis.anifox.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WatchedAnimeDto {

    private Long animeId;

    private String title;

    private Integer status;

    private Integer rating;


}
