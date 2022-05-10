package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.anifox.dto.SelectAnimeDto;
import ru.itis.anifox.services.MyAnimeListService;

import javax.servlet.http.HttpServletRequest;
import static ru.itis.anifox.constants.PageUrlConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/anime-list")
@PreAuthorize("isAuthenticated()")
public class MyAnimeListController {

    private final MyAnimeListService myAnimeListService;

    @PostMapping
    public String addAnimeToList(@RequestBody SelectAnimeDto animeDto, HttpServletRequest request) {
        myAnimeListService.addAnimeToList(animeDto, request);
        return "redirect:" + REDIRECT_MY_ANIME_LIST;
    }

    @GetMapping
    public String getMyAnimeList(Model model, HttpServletRequest request) {
        model.addAttribute("animeList", myAnimeListService.getWatchedAnimeList(request));
        return MY_ANIME_LIST_PAGE;
    }

}
