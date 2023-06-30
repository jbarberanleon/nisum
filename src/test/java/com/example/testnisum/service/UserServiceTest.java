package com.example.testnisum.service;

import com.example.testnisum.handler.exception.InvalidEmailException;
import com.example.testnisum.handler.exception.InvalidPasswordException;
import com.example.testnisum.handler.exception.UserNotFoundException;
import com.example.testnisum.helpers.UtilData;
import com.example.testnisum.mapper.UserMapper;
import com.example.testnisum.repository.UserRepository;
import com.example.testnisum.repository.UserRepositoryTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class, UserMapper.class, UserRepository.class}, initializers = ConfigDataApplicationContextInitializer.class)
public class UserServiceTest {
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    @Test
    void shouldReturnInvalidPasswordException() {
        //given
        var request = UtilData.generateInvalidPasswordRequest();

        //when
        when(userRepository.searchByEmail(request.getEmail())).thenReturn(null);
        when(userService.register(request)).thenThrow(InvalidPasswordException.class);

        //then
        assertThrows(InvalidPasswordException.class,
                ()-> userService.register(request));
    }
    @Test
    void shouldReturnInvalidEmailException() {
        //given
        var request = UtilData.generateInvalidEmailRequest();

        //when
        when(userRepository.searchByEmail(request.getEmail())).thenReturn(null);
        when(userService.register(request)).thenThrow(InvalidEmailException.class);

        //then
        assertThrows(InvalidEmailException.class,
                ()-> userService.register(request));
    }
    @Test
    void shouldReturnUserNotFoundException() {
        //given
        var request = UtilData.generateUserNotFoundRequest();

        //when
        when(userRepository.searchByEmail(request.getEmail())).thenReturn(null);
        when(userService.register(request)).thenThrow(UserNotFoundException.class);

        //then
        assertThrows(UserNotFoundException.class,
                ()-> userService.register(request));
    }
    @Test
    void shouldReturnResponseUserRegisterOk() {
        //given
        var response = UtilData.generateValidUserRegisterRequest();

        //when
        when(userRepository.searchByEmail(response.getEmail())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(UtilData.generateValidUser());
        when(userMapper.fromCreateRequestToUser(any())).thenReturn(UtilData.generateValidUser());
        when(userService.register(response)).thenReturn(UtilData.generateValidUserResponseDto());

        //then
        var returnedUser = userService.register(response);
        assertThat(returnedUser.getUserName(), Matchers.equalTo(response.getUserName()));
    }
    @Test
    void shouldReturnResponseUpdateUserOk() {
        //given
        var response = UtilData.generateValidUpdateUserRequest();

        //when
        when(userRepository.searchByEmail(response.getEmail())).thenReturn(UtilData.generateValidUser());
        when(userRepository.save(any())).thenReturn(UtilData.generateValidUser());
        when(userMapper.fromCreateRequestToUser(any())).thenReturn(UtilData.generateValidUser());
        when(userService.register(response)).thenReturn(UtilData.generateValidUserResponseDto());

        //then
        var returnedUser = userService.register(response);
        assertThat(returnedUser, Matchers.notNullValue());
    }
}
