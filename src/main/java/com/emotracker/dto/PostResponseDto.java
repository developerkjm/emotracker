package com.emotracker.dto;


import com.emotracker.domain.Post;
import com.emotracker.util.IpUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String fileName;
    private String ipAddress;

    private LocalDateTime createdAt;


    public PostResponseDto(Post saved) {
    }

    public String getImageUrl() {
        return (fileName != null && !fileName.isBlank())
                ? "http://localhost:8080/files/" + fileName
                : null;
    }
}



