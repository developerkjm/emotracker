// src/main/java/com/emotracker/controller/EmotionRecordController.java

package com.emotracker.controller;

import com.emotracker.domain.EmotionRecord;
import com.emotracker.dto.EmotionRecordRequestDto;
import com.emotracker.dto.EmotionRecordResponseDto;
import com.emotracker.dto.EmotionResponseDto;
import com.emotracker.repository.EmotionRecordRepository;
import com.emotracker.service.EmotionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emotions")
@RequiredArgsConstructor
public class EmotionRecordController {

    private final EmotionRecordService emotionRecordService;
    private final EmotionRecordRepository emotionRecordRepository;

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

    @GetMapping("/{id}")
    // 감정 기록을 이모지로 표현
    public EmotionResponseDto getEmotion(@PathVariable Long id) {
        EmotionRecord record = emotionRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("감정 기록을 찾을 수 없습니다."));

        return new EmotionResponseDto(record.getEmotionType());
    }

    // 날짜 범위에 맞는 감정 기록을 가져오는 API
    @GetMapping("/date-range")
    public List<EmotionRecordResponseDto> getEmotionRecordsByDateRange(
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {


        List<EmotionRecord> records = emotionRecordService.getEmotionRecordsByDateRange(startDate, endDate);

        return records.stream()
                .map(EmotionRecordResponseDto::new) // EmotionRecord를 EmotionRecordResponseDto로 변환
                .collect(Collectors.toList());
    }

}

