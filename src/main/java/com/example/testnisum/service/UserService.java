package com.example.testnisum.service;

import com.example.testnisum.domain.User;
import com.example.testnisum.dto.requests.create.CreateUserRequestDto;
import com.example.testnisum.dto.requests.login.LoginRequestDto;
import com.example.testnisum.dto.requests.update.UpdateUserRequestDto;
import com.example.testnisum.dto.responses.UserResponseDto;
import com.example.testnisum.handler.exception.*;
import com.example.testnisum.helpers.HelperValidator;
import com.example.testnisum.mapper.UserMapper;
import com.example.testnisum.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;


    @Value("${error.message.already-registered-user}")
    private String alreadyRegisteredUserMessage;
    @Value("${error.message.user-not-found}")
    private String userNotFoundMessage;
    @Value("${error.message.invalid-email}")
    private String invalidEmailMessage;
    @Value("${error.message.invalid-password}")
    private String invalidPasswordMessage;
    @Value("${app.configurations.email-pattern}")
    private String emailRegex;
    @Value("${app.configurations.password-pattern}")
    private String passwordRegex;

    public UserResponseDto register(CreateUserRequestDto createUserRequestDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CreateUserRequestDto>> violations = validator.validate(createUserRequestDto);
        if (!violations.isEmpty()) {
            throw new InvalidFieldsException(violations);
        }
        if(!HelperValidator.isValidPattern(createUserRequestDto.getEmail(), emailRegex)) {
            throw new InvalidEmailException(invalidEmailMessage);
        }
        if(!HelperValidator.isValidPattern(createUserRequestDto.getPassword(), passwordRegex)) {
            throw new InvalidPasswordException(invalidPasswordMessage);
        }
        var user = userRepository.searchByEmail(createUserRequestDto.getEmail());
        if(!Objects.isNull(user)) {
            throw new AlreadyRegisteredUserException(alreadyRegisteredUserMessage);
        }
        user = userMapper.fromCreateRequestToUser(createUserRequestDto);
        return save(user);
    }
    public UserResponseDto register(UpdateUserRequestDto updateUserRequestDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UpdateUserRequestDto>> violations = validator.validate(updateUserRequestDto);
        if (!violations.isEmpty()) {
            throw new InvalidFieldsException(violations);
        }
        if(!HelperValidator.isValidPattern(updateUserRequestDto.getPassword(), passwordRegex)) {
            throw new InvalidPasswordException(invalidPasswordMessage);
        }
        var user = userRepository.searchByEmail(updateUserRequestDto.getEmail());
        if(Objects.isNull(user)) {
            throw new UserNotFoundException(userNotFoundMessage);
        }
        user = userMapper.fromUpdateRequestToUser(user, updateUserRequestDto);
        return save(user);
    }
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);
        if (!violations.isEmpty()) {
            throw new InvalidFieldsException(violations);
        }

        var user = userRepository.searchByEmailAndPassword(loginRequestDto.getEmail(), Hashing.sha256()
                .hashString(loginRequestDto.getPassword(), StandardCharsets.UTF_8)
                .toString());
        if(Objects.isNull(user)) {
            throw new UserNotFoundException(userNotFoundMessage);
        }
        user = userMapper.fromLoginRequestToUser(user);
        return save(user);
    }

    private UserResponseDto save(User user) {
        userRepository.save(user);
        var record = userMapper.fromUserToResponse(user);
        return record;
    }

}
