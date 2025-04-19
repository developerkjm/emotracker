package com.emotracker.controller;

import com.emotracker.domain.Post;
import com.emotracker.dto.PostRequestDto;
import com.emotracker.dto.PostResponseDto;
import com.emotracker.service.FileService;
import com.emotracker.service.PostService;
import com.emotracker.util.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.emotracker.util.IpUtil.getClientIp;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    /* file 넣기 전
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto dto,
                                      HttpServletRequest request) {
        String ip = IpUtil.getClientIp(request); // IP 추출
        return postService.createPost(dto, ip);
    }
    */


    // file 넣으므로 수정함.
    @PostMapping
    public ResponseEntity<String> createPost(
            @RequestPart("post") PostRequestDto postDto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            HttpServletRequest request
    ) {
        String fileName = null;

        if (file != null && !file.isEmpty()) {
            try {
                fileName = fileService.saveFile(file);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("파일 저장 실패: " + e.getMessage());
            }
        }

        String ipAddress = getClientIp(request); // IP 추출
        postService.createPost(postDto, ipAddress, fileName);
        return ResponseEntity.ok("게시글 등록 성공!");
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        System.out.println("id 안들어옴?? " + "{id}");
        return postService.getPostById(id);
    }


}
