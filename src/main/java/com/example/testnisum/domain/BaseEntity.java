package com.example.testnisum.domain;

import com.example.testnisum.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseEntity {

	@Id
	@UuidGenerator
	private UUID id;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "CREATED", nullable = false)
	private LocalDateTime created;

	@Column(name = "MODIFIED")
	private LocalDateTime modified;
}