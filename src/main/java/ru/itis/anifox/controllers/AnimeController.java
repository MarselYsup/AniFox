package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.anifox.services.AnimeService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/anime")
@PreAuthorize("isAuthenticated()")
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public String getAllAnimePage(Model model) {
        model.addAttribute("animes", animeService.getAllAnime());
        return "animeList";
    }

    @GetMapping("/{anime-id}")
    public String getAnimePage(@PathVariable("anime-id") Long animeId, Model model, HttpServletRequest request) {
        model.addAttribute("anime",animeService.getAnime(animeId));
        model.addAttribute("status", animeService.getStatus(animeId, request));
        return "anime";
    }

}
