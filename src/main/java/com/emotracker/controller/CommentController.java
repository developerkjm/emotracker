package com.emotracker.controller;

import com.emotracker.dto.CommentRequestDto;
import com.emotracker.dto.CommentResponseDto;
import com.emotracker.service.CommentService;
import com.emotracker.util.IpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping
    public CommentResponseDto saveComment(@RequestBody CommentRequestDto dto,
                                          HttpServletRequest request) {
        String ip = IpUtil.getClientIp(request);
        return commentService.saveComment(dto, ip);
    }

    // 댓글 목록 조회 (게시글 기준)
    @GetMapping
    public List<CommentResponseDto> getComments(@RequestParam Long postId,
                                                @RequestParam(required = false, defaultValue = "false") boolean isAdmin) {
        return commentService.getCommentsByPost(postId, isAdmin);
    }
}
