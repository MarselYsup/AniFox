package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.anifox.dto.AddAnimeDto;
import ru.itis.anifox.services.AnimeService;
import ru.itis.anifox.services.GenreService;
import ru.itis.anifox.services.StudioService;



import static ru.itis.anifox.constants.MessageConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/add-anime")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AddAnimeController {

    private final AnimeService animeService;

    private final StudioService studioService;

    private final GenreService genreService;

    @GetMapping
    public String animeAddPage(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("studios", studioService.getAllStudio());
        return "addAnime";
    }

    @PostMapping
    public String createAnime(@RequestParam("file") MultipartFile file, AddAnimeDto animeDto,
                              RedirectAttributes redirectAttributes) {
        animeService.addAnime(file, animeDto);
        redirectAttributes.addAttribute("message", SUCCESSFULLY_ADDING_ANIME);
        return "redirect:/add-anime";
    }


}
