package com.example.testnisum.dto.requests.update;

import com.example.testnisum.domain.enums.Status;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class UpdateUserRequestDto {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
    private List<Map<String, Object>> phones;
    @NotNull
    private Status status;
}
