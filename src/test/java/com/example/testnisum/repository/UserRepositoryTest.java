package com.example.testnisum.repository;

import com.example.testnisum.handler.exception.InvalidPasswordException;
import com.example.testnisum.helpers.UtilData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserRepository.class}, initializers = ConfigDataApplicationContextInitializer.class)
public class UserRepositoryTest {
    @MockBean
    private UserRepository userRepository;
    @Test
    void shouldReturnUserWhenSearchByEmail() {
        //given
        var request = UtilData.generateValidUserRegisterRequest();

        //when
        when(userRepository.searchByEmail(request.getEmail())).thenReturn(UtilData.generateValidUser());
        var returnedUser = userRepository.searchByEmail(request.getEmail());

        //then
        assertThat(returnedUser, Matchers.notNullValue());
    }

    @Test
    void shouldReturnUserWhenSearchByEmailAndPassword() {
        //given
        var request = UtilData.generateValidUserRegisterRequest();

        //when
        when(userRepository.searchByEmailAndPassword(request.getEmail(), request.getPassword())).thenReturn(UtilData.generateValidUser());
        var returnedUser = userRepository.searchByEmailAndPassword(request.getEmail(), request.getPassword());

        //then
        assertThat(returnedUser, Matchers.notNullValue());
    }

}
