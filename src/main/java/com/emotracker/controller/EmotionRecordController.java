// src/main/java/com/emotracker/controller/EmotionRecordController.java

package com.emotracker.controller;

import com.emotracker.dto.EmotionRecordRequestDto;
import com.emotracker.dto.EmotionRecordResponseDto;
import com.emotracker.service.EmotionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emotions")
@RequiredArgsConstructor
public class EmotionRecordController {

    private final EmotionRecordService emotionRecordService;

    // 감정 기록 저장
    @PostMapping("/save")
    public EmotionRecordResponseDto saveEmotion(@RequestBody EmotionRecordRequestDto dto) {
        Long fakeUserId = 1L; // ★ 로그인 기능 없으므로 우선 고정된 사용자 ID 사용
        return emotionRecordService.saveEmotionRecord(dto, fakeUserId);
    }

    // 감정 기록 전체 조회 (특정 사용자 기준)
    @GetMapping("/list")
    public List<EmotionRecordResponseDto> getUserEmotions() {
        Long fakeUserId = 1L; // ★ 로그인 기능 없으므로 우선 고정된 사용자 ID 사용
        return emotionRecordService.getEmotionRecordsByUser(fakeUserId);
    }
}
