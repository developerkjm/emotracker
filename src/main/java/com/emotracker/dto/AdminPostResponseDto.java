package com.emotracker.dto;

import com.emotracker.domain.Post;

import java.time.LocalDateTime;

public class AdminPostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String fullIp;
    private LocalDateTime createdAt;

    public AdminPostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.fullIp = post.getIpAddress(); // 마스킹 없이 원본 IP
        this.createdAt = post.getCreatedAt();
    }
}
