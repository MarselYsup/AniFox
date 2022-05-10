package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static ru.itis.anifox.constants.PageUrlConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sign-in")
public class SignInController {


    @GetMapping
    public String getSignInPage(Authentication authentication) {
        if(authentication != null) {
            return "redirect:" + REDIRECT_PROFILE_URL;
        }
        return SIGN_IN_PAGE;
    }
}
