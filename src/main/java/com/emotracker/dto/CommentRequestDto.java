package com.emotracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long postId;
    private String content;
    private String writer;
    private Long parentId; // 대댓글일 경우 부모 댓글 ID
}
