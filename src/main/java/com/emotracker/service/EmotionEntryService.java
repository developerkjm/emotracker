package com.emotracker.service;

import com.emotracker.domain.EmotionEntry;
import com.emotracker.dto.CalendarEmotionDto;
import com.emotracker.dto.EmotionEntryDto;
import com.emotracker.repository.EmotionEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmotionEntryService {

    private final EmotionEntryRepository repository;

    public void save(EmotionEntryDto dto) {
        EmotionEntry entry = new EmotionEntry();
        entry.setEmotion(dto.getEmotion());
        entry.setMemo(dto.getMemo());
        entry.setDate(dto.getDate());
        repository.save(entry);
    }

    public List<CalendarEmotionDto> getAllForCalendar() {
        return repository.findAll().stream()
                .map(entry -> new CalendarEmotionDto(
                        entry.getId(),
                        entry.getEmotion(),              // title (이모지)
                        entry.getDate().toString(),      // start (날짜)
                        entry.getMemo()                  // memo
                ))
                .collect(Collectors.toList());
    }

    public void update(Long id, EmotionEntryDto dto) {
        EmotionEntry entry = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("감정 기록이 존재하지 않음"));

        entry.setEmotion(dto.getEmotion());
        entry.setMemo(dto.getMemo());
        entry.setDate(dto.getDate());

        repository.save(entry);
    }


}
