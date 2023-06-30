package com.example.testnisum.configuration;

import com.example.testnisum.configuration.security.SecurityAuthorizationFilter;
import com.example.testnisum.handler.ControllerExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class ConfigurationApplication {
    private final SecurityAuthorizationFilter securityAuthorizationFilter;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .addFilterAfter(securityAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers("/swagger-ui/**",
                            "/swagger-resources/*",
                            "/api-docs/**")
                            .permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.POST, "/nisum/user/register").permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.POST, "/nisum/user/login").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }
            ).exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new ControllerExceptionHandler()));
        return http.build();
    }
}
