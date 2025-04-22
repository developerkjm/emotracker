package com.emotracker.service;

import com.emotracker.domain.Post;
import com.emotracker.domain.User;
import com.emotracker.dto.PostRequestDto;
import com.emotracker.dto.PostResponseDto;
import com.emotracker.repository.CommentRepository;
import com.emotracker.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Long createPost(PostRequestDto dto, String ipAddress, String fileName) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getWriter());
        post.setIpAddress(ipAddress);
        post.setFileName(fileName);
        // createdAt도 자동 저장되게 설정해놨겠지? 😎

        Post saved = postRepository.save(post);
        return saved.getId(); // ✅ ID만 리턴!
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

    public void updatePost(Long id, String title, String content, String writer) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않음"));
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(writer);
        postRepository.save(post);
    }


    @Transactional
    public void deletePost(Long postId) {
        commentRepository.deleteByPostId(postId); // 댓글 먼저 삭제
        postRepository.deleteById(postId);        // 그 다음 게시글 삭제
    }




}


