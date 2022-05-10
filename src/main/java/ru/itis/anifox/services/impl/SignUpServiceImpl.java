package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.itis.anifox.dto.SignUpDto;
import ru.itis.anifox.exception.already_exist.MailAlreadyExistException;
import ru.itis.anifox.exception.not_found.UserNotFoundException;
import ru.itis.anifox.mapstruct.impl.SignUpMapstruct;
import ru.itis.anifox.models.UserEntity;
import ru.itis.anifox.models.enums.State;
import ru.itis.anifox.repositories.UserRepository;
import ru.itis.anifox.services.SignUpService;
import ru.itis.anifox.util.email.EmailSender;
import ru.itis.anifox.util.email.MailGenerator;
import ru.itis.anifox.validation.model.ValidError;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    private final SignUpMapstruct signUpMapstruct;

    private final EmailSender emailSender;

    @Override
    @Transactional
    public void signUp(SignUpDto signUpDto) {
        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new MailAlreadyExistException();
        }

        UserEntity user = userRepository.save(signUpMapstruct.convertTo(signUpDto));
        emailSender.sendMessage(MailGenerator.generateConfirmSignUpMessage(user));
    }

    @Override
    @Transactional
    public void acceptConfirmCode(String confirmCode) {
        UserEntity user = userRepository.findByConfirmCode(confirmCode)
                .orElseThrow(UserNotFoundException::new);
        user.setConfirmCode(null);
        user.setState(State.CONFIRMED);
        userRepository.save(user);
    }

    @Override
    public List<ValidError> getAllValidErrorFromForm(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map( e -> {
                    if (e instanceof FieldError) {
                        FieldError error = (FieldError) e;
                        return new ValidError(error.getField() + " : " + error.getDefaultMessage());
                    }
                    return new ValidError(e.getObjectName() + " : " +  e.getDefaultMessage());
                })
                .collect(Collectors.toList());
    }
}
