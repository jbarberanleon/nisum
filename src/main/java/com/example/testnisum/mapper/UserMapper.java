package com.example.testnisum.mapper;

import com.example.testnisum.domain.User;
import com.example.testnisum.domain.enums.Status;
import com.example.testnisum.dto.requests.create.CreateUserRequestDto;
import com.example.testnisum.dto.requests.update.UpdateUserRequestDto;
import com.example.testnisum.dto.responses.UserResponseDto;
import com.example.testnisum.helpers.GeneratorToken;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User fromCreateRequestToUser(CreateUserRequestDto createUserRequestDto) {
        return User.builder()
                .userName(createUserRequestDto.getUserName())
                .email(createUserRequestDto.getEmail())
                .name(createUserRequestDto.getName())
                .password(Hashing.sha256()
                        .hashString(createUserRequestDto.getPassword(), StandardCharsets.UTF_8)
                        .toString())
                .phones(createUserRequestDto.getPhones())
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(GeneratorToken.getToken(createUserRequestDto.getEmail()))
                .status(Status.ACTIVE)
                .build();
    }

    public User fromUpdateRequestToUser(User user, UpdateUserRequestDto updateUserRequestDto) {
        return User.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .name(updateUserRequestDto.getName())
                .password(Hashing.sha256()
                        .hashString(updateUserRequestDto.getPassword(), StandardCharsets.UTF_8)
                        .toString())
                .phones(updateUserRequestDto.getPhones())
                .created(user.getCreated())
                .modified(LocalDateTime.now())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .status(updateUserRequestDto.getStatus())
                .build();
    }

    public User fromLoginRequestToUser(User user) {
        return User.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .phones(user.getPhones())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(LocalDateTime.now())
                .token(GeneratorToken.getToken(user.getEmail()))
                .status(user.getStatus())
                .build();
    }

    public UserResponseDto fromUserToResponse(User user) {
        return UserResponseDto.builder()
                .userName(user.getUserName())
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.getStatus())
                .build();
    }
}
