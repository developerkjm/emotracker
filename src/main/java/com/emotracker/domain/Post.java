package com.emotracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;

    private String ipAddress; // 작성자 IP

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private String fileName; // 첨부파일 이름


    // 생성자 등 생략
}
