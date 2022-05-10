package ru.itis.anifox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.anifox.validation.annotations.PasswordFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpDto {

    @Email(message = "Email must be valid!")
    @NotBlank(message = "The field must be filled!")
    private String email;

    @PasswordFormat
    @NotBlank(message = "The field must be filled!")
    private String password;
}
