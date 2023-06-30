package com.example.testnisum.repository;

import com.example.testnisum.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User searchByEmail(String email);

    User searchByEmailAndPassword(String email, String password);
}
