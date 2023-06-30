package com.example.testnisum.domain;
import com.example.testnisum.helpers.HelperConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(name = "PHONES", nullable = false)
    @Convert(converter = HelperConverter.class)
    private List<Map<String, Object>> phones;

    @Column(name = "TOKEN")
    private String token;
}
