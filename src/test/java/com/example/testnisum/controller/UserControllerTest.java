package com.example.testnisum.controller;

import com.example.testnisum.dto.requests.create.CreateUserRequestDto;
import com.example.testnisum.dto.requests.update.UpdateUserRequestDto;
import com.example.testnisum.dto.responses.UserResponseDto;
import com.example.testnisum.helpers.GeneratorToken;
import com.example.testnisum.helpers.UtilData;
import com.example.testnisum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = {UserService.class, UserMapper.class, UserRepository.class}, initializers = ConfigDataApplicationContextInitializer.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        Mockito.reset(userService);
    }
    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }
    @Test
    void shouldRegisterOk() {
        //given
        var request = UtilData.generateValidUserRegisterRequest();
        var response = UtilData.generateValidUserResponseDto();
        //when
        when(userService.register(any(CreateUserRequestDto.class))).thenReturn(response);

        //then
        webClient.post()
                .uri("/nisum/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userName").isEqualTo(response.getUserName());
    }

    @Test
    void shouldUpdateNotAuthorized() {
        //given
        var request = UtilData.generateValidUserRegisterRequest();
        var response = UtilData.generateValidUserResponseDto();
        //when
        when(userService.register(any(CreateUserRequestDto.class))).thenReturn(response);

        //then
        webClient.put()
                .uri("/nisum/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldUpdateOk() {
        //given
        var request = UtilData.generateValidUserRegisterRequest();
        var response = UtilData.generateValidUserResponseDto();
        //when
        when(userService.register(any(CreateUserRequestDto.class))).thenReturn(response);

        //then
        webClient.put()
                .uri("/nisum/user/register")
                .headers(http -> http.setBearerAuth(GeneratorToken.getToken(request.getEmail())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), UpdateUserRequestDto.class)
                .exchange()
                .expectStatus().isOk();
    }


}
