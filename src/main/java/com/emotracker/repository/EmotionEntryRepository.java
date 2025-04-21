package com.emotracker.repository;

import com.emotracker.domain.EmotionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionEntryRepository extends JpaRepository<EmotionEntry, Long> {
    // 기본 CRUD는 JpaRepository가 다 제공해줌!
}
