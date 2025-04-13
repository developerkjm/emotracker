// src/main/java/com/emotracker/repository/UserRepository.java

package com.emotracker.repository;

import com.emotracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // User 엔티티는 Long 타입의 id를 가짐
}
