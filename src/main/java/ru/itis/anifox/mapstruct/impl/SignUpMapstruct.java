package ru.itis.anifox.mapstruct.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.anifox.dto.SignUpDto;
import ru.itis.anifox.mapstruct.BaseMapstruct;
import ru.itis.anifox.models.UserEntity;
import ru.itis.anifox.models.enums.Role;
import ru.itis.anifox.models.enums.State;
import ru.itis.anifox.util.email.CodeGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SignUpMapstruct implements BaseMapstruct<SignUpDto, UserEntity> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity convertTo(SignUpDto signUpDto) {
        return UserEntity.builder()
                .email(signUpDto.getEmail())
                .confirmCode(CodeGenerator.generate())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .build();
    }

    @Override
    public SignUpDto convertFrom(UserEntity userEntity) {
        return SignUpDto.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    public List<UserEntity> convertTo(List<SignUpDto> signUpDtos) {
        return signUpDtos.stream().map(this::convertTo).collect(Collectors.toList());
    }

    @Override
    public List<SignUpDto> convertFrom(List<UserEntity> userEntities) {
        return userEntities.stream().map(this::convertFrom).collect(Collectors.toList());
    }
}
