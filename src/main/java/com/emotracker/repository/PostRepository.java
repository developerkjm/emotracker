package com.emotracker.repository;

import com.emotracker.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 필요하면 커스텀 쿼리 메서드 추가 가능
}