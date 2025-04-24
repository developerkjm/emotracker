// src/main/java/com/emotracker/repository/UserRepository.java

package com.emotracker.repository;

import com.emotracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // User 엔티티는 Long 타입의 id를 가짐

    Optional<User> findByEmail(String email); // ★ 이메일로 유저 찾기
    Optional<User> findByUsername(String username);


}
