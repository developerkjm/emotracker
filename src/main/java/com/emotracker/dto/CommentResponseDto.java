package com.emotracker.dto;

import com.emotracker.domain.Comment;
import com.emotracker.util.IpUtil;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String writer;
    private String maskedIp;
    private LocalDateTime createdAt;
    private Long parentId;

    public CommentResponseDto(Comment comment, boolean isAdmin) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.createdAt = comment.getCreatedAt();
        this.parentId = comment.getParent() != null ? comment.getParent().getId() : null;

        this.maskedIp = isAdmin ? comment.getIpAddress() : IpUtil.maskIp(comment.getIpAddress());
    }
}
