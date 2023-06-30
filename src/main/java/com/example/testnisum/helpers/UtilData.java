package com.example.testnisum.helpers;

import com.example.testnisum.domain.User;
import com.example.testnisum.dto.requests.create.CreateUserRequestDto;
import com.example.testnisum.dto.requests.update.UpdateUserRequestDto;
import com.example.testnisum.dto.responses.UserResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UtilData {
    public static CreateUserRequestDto generateInvalidPasswordRequest() {
        return CreateUserRequestDto.builder()
                .userName("jheart")
                .email("jheart@nisum.cl")
                .name("John Heart")
                .password("Abc123")
                .build();
    }

    public static CreateUserRequestDto generateInvalidEmailRequest() {
        return CreateUserRequestDto.builder()
                .userName("jheart")
                .email("jhear.cl")
                .name("John Heart")
                .password("Abc123")
                .build();
    }

    public static UpdateUserRequestDto generateUserNotFoundRequest() {
        return UpdateUserRequestDto.builder()
                .email("jhear.cl")
                .name("John Heart")
                .password("Abc123")
                .build();
    }

    public static UpdateUserRequestDto generateValidUpdateUserRequest() {
        return UpdateUserRequestDto.builder()
                .email("jhear@.nisum.cl")
                .name("John Heart")
                .password("Jj170192")
                .build();
    }
    public static CreateUserRequestDto generateValidUserRegisterRequest() {
        return CreateUserRequestDto.builder()
                .userName("jheart")
                .email("jheart@nisum.cl")
                .name("John Heart")
                .password("Jj170192")
                .build();
    }


    public static User generateValidUser() {
        return User.builder()
                .id(UUID.randomUUID())
                .userName("jheart")
                .lastLogin(LocalDateTime.now())
                .created(LocalDateTime.now())
                .token(GeneratorToken.getToken("jheart@nisum.cl"))
                .build();
    }

    public static UserResponseDto generateValidUserResponseDto() {
        return UserResponseDto.builder()
                .id(UUID.randomUUID())
                .userName("jheart")
                .lastLogin(LocalDateTime.now())
                .created(LocalDateTime.now())
                .token(GeneratorToken.getToken("jheart@nisum.cl"))
                .build();
    }
}
