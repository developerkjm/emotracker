package com.emotracker.service;

import com.emotracker.domain.Comment;
import com.emotracker.domain.Post;
import com.emotracker.dto.CommentRequestDto;
import com.emotracker.dto.CommentResponseDto;
import com.emotracker.repository.CommentRepository;
import com.emotracker.repository.PostRepository;
import com.emotracker.util.IpUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto saveComment(CommentRequestDto dto, String ipAddress) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment parent = null;
        if (dto.getParentId() != null) {
            parent = commentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("부모 댓글을 찾을 수 없습니다."));
        }

        Comment comment = Comment.builder()
                .post(post)
                .content(dto.getContent())
                .writer(dto.getWriter())
                .ipAddress(ipAddress)
                .createdAt(LocalDateTime.now())
                .parent(parent)
                .build();

        commentRepository.save(comment);

        return new CommentResponseDto(comment, false); // 기본은 일반 사용자 응답
    }

    public List<CommentResponseDto> getCommentsByPost(Long postId, boolean isAdmin) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(c -> new CommentResponseDto(c, isAdmin))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + id));

        comment.setContent(dto.getContent());  // 내용 수정
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + id));
        commentRepository.delete(comment);
    }


}
