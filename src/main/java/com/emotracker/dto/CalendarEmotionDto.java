package com.emotracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalendarEmotionDto {
    private Long id;
    private String title; // 감정 이모지
    private String start; // 날짜 (yyyy-MM-dd)
    private String memo;  // 감정에 대한 메모
}
