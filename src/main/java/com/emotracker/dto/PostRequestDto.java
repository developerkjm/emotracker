package com.emotracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private String writer;
}
