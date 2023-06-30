package com.example.testnisum.controller;

import com.example.testnisum.dto.requests.create.CreateUserRequestDto;
import com.example.testnisum.dto.requests.login.LoginRequestDto;
import com.example.testnisum.dto.requests.update.UpdateUserRequestDto;
import com.example.testnisum.dto.responses.UserResponseDto;
import com.example.testnisum.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nisum/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto register(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.register(createUserRequestDto);
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto register(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return userService.register(updateUserRequestDto);
    }

}
