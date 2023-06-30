package com.example.testnisum.dto.responses;

import com.example.testnisum.domain.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserResponseDto {
    private UUID id;
    private String userName;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Status isActive;
}
