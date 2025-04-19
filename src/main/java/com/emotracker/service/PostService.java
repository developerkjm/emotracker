package com.emotracker.service;

import com.emotracker.domain.Post;
import com.emotracker.domain.User;
import com.emotracker.dto.PostRequestDto;
import com.emotracker.dto.PostResponseDto;
import com.emotracker.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto dto, String ipAddress, String fileName) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getWriter());
        post.setIpAddress(ipAddress); // IP 저장
        post.setFileName(fileName);

        Post saved = postRepository.save(post);
        return new PostResponseDto(saved); // 응답 DTO로 변환
    }

    public Object getPost(Long id, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

            return new PostResponseDto(post); // 마스킹된 DTO
    }

    /*
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor(),
                        post.getFileName(),
                        post.getIpAddress()
                ))
                .collect(Collectors.toList());
    }
     */

    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID: " + id));

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getFileName(),
                post.getIpAddress(),
                post.getCreatedAt()
        );
    }

    public List<Post> getAllPosts() {
        System.out.println("포스트 서비스!!!");
        return postRepository.findAllByOrderByIdDesc(); // 최신글 위로
    }


}


