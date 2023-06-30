package com.example.testnisum.dto.requests.login;
import javax.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Email;

@Data
public class LoginRequestDto {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
