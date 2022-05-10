package ru.itis.anifox.services;

import org.springframework.validation.BindingResult;
import ru.itis.anifox.dto.SignUpDto;
import ru.itis.anifox.validation.model.ValidError;

import java.util.List;

public interface SignUpService {
    void signUp(SignUpDto signUpDto);

    void acceptConfirmCode(String confirmCode);

    List<ValidError> getAllValidErrorFromForm(BindingResult bindingResult);
}
