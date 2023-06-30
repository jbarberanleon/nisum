package com.example.testnisum.dto.requests.create;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class CreateUserRequestDto {
    @NotNull
    private String userName;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private List<Map<String, Object>> phones;
}
