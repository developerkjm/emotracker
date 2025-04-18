package com.emotracker.dto;


import com.emotracker.domain.Post;
import com.emotracker.util.IpUtil;
import lombok.Getter;


@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String maskedIp;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getAuthor();
//        this.maskedIp = IpUtil.maskIp(post.getIpAddress()); // 마스킹 처리
        this.maskedIp = IpUtil.maskIp(post.getIpAddress());
    }
}


