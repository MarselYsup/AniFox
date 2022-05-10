package ru.itis.anifox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.anifox.dto.SignUpDto;
import ru.itis.anifox.services.SignUpService;

import javax.validation.Valid;

import static ru.itis.anifox.constants.PageUrlConstants.*;
import static ru.itis.anifox.constants.MessageConstants.*;
@Controller
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Authentication authentication) {
        if(authentication != null) {
            return "redirect:" + REDIRECT_PROFILE_URL;
        }
        return SIGN_UP_PAGE;
    }

    @PostMapping
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult,  Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", signUpService.getAllValidErrorFromForm(bindingResult));
            return SIGN_UP_PAGE;
        }
        signUpService.signUp(signUpDto);
        model.addAttribute("message", SUCCESSFULLY_REGISTERED_MESSAGE);
        return SIGN_IN_PAGE;
    }

    @GetMapping("/{confirm-link}")
    public String acceptConfirmLink(@PathVariable("confirm-link") String confirmCode, Model model) {
        signUpService.acceptConfirmCode(confirmCode);
        model.addAttribute("message", SUCCESSFULLY_CONFIRMED_MESSAGE);
        return SIGN_IN_PAGE;
    }
}
