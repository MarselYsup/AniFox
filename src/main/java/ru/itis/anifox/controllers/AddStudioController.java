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
import ru.itis.anifox.dto.AddStudioDto;
import ru.itis.anifox.services.StudioService;

import static ru.itis.anifox.constants.MessageConstants.*;


@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/add-studio")
public class AddStudioController {

    private final StudioService studioService;


    @GetMapping
    public String studioAddPage() {
        return "addStudio";
    }

    @PostMapping
    public String createStudio(@RequestParam("file") MultipartFile file, AddStudioDto studioDto, Model model) {
        studioService.addStudio(file, studioDto);
        model.addAttribute("message", SUCCESSFULLY_ADDING_STUDIO);
        return "addStudio";
    }

}
