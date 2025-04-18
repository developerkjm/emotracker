package com.emotracker.service;

import com.emotracker.domain.Post;
import com.emotracker.domain.User;
import com.emotracker.dto.PostRequestDto;
import com.emotracker.dto.PostResponseDto;
import com.emotracker.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto dto, String ipAddress) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getWriter());
        post.setIpAddress(ipAddress); // IP 저장

        Post saved = postRepository.save(post);
        return new PostResponseDto(saved); // 응답 DTO로 변환
    }

    public Object getPost(Long id, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

            return new PostResponseDto(post); // 마스킹된 DTO
    }
}


