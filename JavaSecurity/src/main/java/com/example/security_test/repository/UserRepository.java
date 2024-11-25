package com.example.security_test.repository;

import com.example.security_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 알아서 JPA에서 만들어줌~
    public User findByUsername(String username);
}
