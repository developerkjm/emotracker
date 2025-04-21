package com.emotracker.controller;

import com.emotracker.dto.CalendarEmotionDto;
import com.emotracker.dto.EmotionEntryDto;
import com.emotracker.service.EmotionEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmotionEntryApiController {

    private final EmotionEntryService emotionEntryService;

    @GetMapping("/emotion-entries")
    public List<CalendarEmotionDto> getEmotionEntries() {
        return emotionEntryService.getAllForCalendar();
    }

    @PutMapping("/emotion-entries/{id}")
    public ResponseEntity<String> updateEmotionEntry(
            @PathVariable Long id,
            @RequestBody EmotionEntryDto dto) {

        emotionEntryService.update(id, dto);
        return ResponseEntity.ok("수정 성공!");
    }

    @PostMapping("/emotion-entries")
    public ResponseEntity<String> create(@RequestBody EmotionEntryDto dto) {
        emotionEntryService.save(dto);
        return ResponseEntity.ok("등록 성공");
    }


}
