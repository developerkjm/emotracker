package com.emotracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private String content;
    private String writer;
    private String ipAddress;

    private LocalDateTime createdAt;

    // 대댓글을 위한 자기참조
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;
}
