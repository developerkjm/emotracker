// src/main/java/com/emotracker/repository/EmotionRecordRepository.java

package com.emotracker.repository;

import com.emotracker.domain.EmotionRecord;
import com.emotracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmotionRecordRepository extends JpaRepository<EmotionRecord, Long> {

    // 사용자별 감정 기록 조회
    List<EmotionRecord> findByUser(User user);

    // 사용자 + 날짜 기준으로 감정 기록 조회
    List<EmotionRecord> findByUserAndDate(User user, LocalDate date);

    // 사용자 기준 조회
    List<EmotionRecord> findAllByUser(User user);

    // 날짜 범위에 해당하는 감정 기록 조회 (추가된 메서드)
    List<EmotionRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
