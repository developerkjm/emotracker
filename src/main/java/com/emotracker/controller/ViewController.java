package com.emotracker.controller;

import com.emotracker.domain.Post;
import com.emotracker.dto.PostRequestDto;
import com.emotracker.dto.PostResponseDto;
import com.emotracker.service.FileService;
import com.emotracker.service.PostService;
import com.emotracker.util.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PostService postService;
    private final FileService fileService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> postList = postService.getAllPosts();
        model.addAttribute("postList", postList);
        return "index"; // templates/index.html
    }

    @GetMapping("/posts/write")
    public String showCreateForm() {
        return "create"; // templates/create.html
    }

    @PostMapping("/posts/create")
    public String handleCreate(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request
    ) {
        String ip = IpUtil.getClientIp(request);
        String fileName = null;

        if (file != null && !file.isEmpty()) {
            try {
                fileName = fileService.saveFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PostRequestDto dto = new PostRequestDto(title, content, writer);
        postService.createPost(dto, ip, fileName);

        return "redirect:/";
    }

    @GetMapping("/posts/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        PostResponseDto post = postService.getPostById(id);


        model.addAttribute("post", post);
        return "detail";
    }

}
