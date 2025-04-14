// src/main/java/com/emotracker/service/EmotionRecordService.java

package com.emotracker.service;

import com.emotracker.domain.EmotionRecord;
import com.emotracker.domain.EmotionType;
import com.emotracker.domain.User;
import com.emotracker.dto.EmotionRecordRequestDto;
import com.emotracker.dto.EmotionRecordResponseDto;
import com.emotracker.dto.EmotionResponseDto;
import com.emotracker.repository.EmotionRecordRepository;
import com.emotracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmotionRecordService {

    private final EmotionRecordRepository emotionRecordRepository;
    private final UserRepository userRepository;

    // 감정 기록 저장
    @Transactional
    public EmotionRecordResponseDto saveEmotionRecord(EmotionRecordRequestDto dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));  // 유저가 없다면 예외 발생

        EmotionRecord emotionRecord = EmotionRecord.builder()
                .date(LocalDate.parse(dto.getDate()))
                .emotionType(EmotionType.valueOf(dto.getEmotionType()))
                .memo(dto.getMemo())
                .imageUrl(dto.getImageUrl())
                .user(user)  // 유저 설정
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        EmotionRecord savedRecord = emotionRecordRepository.save(emotionRecord);

        return new EmotionRecordResponseDto(savedRecord);  // DTO로 응답 반환
    }

    // 특정 유저의 감정 기록 조회
    public List<EmotionRecordResponseDto> getEmotionRecordsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<EmotionRecord> records = emotionRecordRepository.findAllByUser(user);

        return records.stream()
                .map(EmotionRecordResponseDto::new)
                .collect(Collectors.toList());
    }

    // 날짜 범위에 해당하는 감정 기록을 가져오는 메서드
    public List<EmotionRecord> getEmotionRecordsByDateRange(LocalDate startDate, LocalDate endDate) {
        return emotionRecordRepository.findByDateBetween(startDate, endDate);
    }



}
