package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.security.details.AccountUserDetails;
import ru.itis.anifox.services.ProfileService;

import javax.servlet.http.HttpServletRequest;

import static ru.itis.anifox.constants.PageUrlConstants.PROFILE_PAGE;
import static ru.itis.anifox.constants.PageUrlConstants.REDIRECT_PROFILE_URL;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails user, Model model,
                                 HttpServletRequest request) {
        if(request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("user", profileService.getUserByEmail(user.getUsername()));
        }
        model.addAttribute("user", request.getSession().getAttribute("user"));

        return PROFILE_PAGE;
    }

    @PostMapping("/avatar")
    @PreAuthorize("isAuthenticated()")
    public String setAvatar(@RequestParam("file") MultipartFile avatar, @AuthenticationPrincipal AccountUserDetails user,
                            HttpServletRequest request) {
        request.getSession().setAttribute("user", profileService.setAvatar(avatar, user.getUsername()));
        return "redirect:"+ REDIRECT_PROFILE_URL;
    }


}
