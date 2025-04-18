package com.emotracker.repository;

import com.emotracker.domain.Comment;
import com.emotracker.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 게시글 기준 댓글 조회
    List<Comment> findByPost(Post post);
    // 부모 댓글 기준 대댓글 조회
    List<Comment> findByParent(Comment parent);
}
