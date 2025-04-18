package com.emotracker.controller;

import com.emotracker.dto.PostRequestDto;
import com.emotracker.dto.PostResponseDto;
import com.emotracker.service.PostService;
import com.emotracker.util.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto dto,
                                      HttpServletRequest request) {
        String ip = IpUtil.getClientIp(request); // IP 추출
        return postService.createPost(dto, ip);
    }
}
