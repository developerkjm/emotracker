package com.emotracker.controller;

import com.emotracker.dto.EmotionEntryDto;
import com.emotracker.dto.EmotionRecordResponseDto;
import com.emotracker.service.EmotionEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/entry")
public class EmotionEntryController {

    private final EmotionEntryService emotionEntryService;

    // 폼 보여주기
    @GetMapping
    public String showForm(Model model) {
//        emotionEntryService.getEmotionRecordsByUser(fakeUserId);
        emotionEntryService.getAllForCalendar();
        model.addAttribute("emotionEntryDto", new EmotionEntryDto()); // 폼용 객체
        model.addAttribute("activePage", "entry"); // 메뉴 하이라이트용
        return "emotionEntry"; // templates/emotionEntry.html
    }
/*
    // 감정 기록 전체 조회 (특정 사용자 기준)
    @GetMapping("/list")
    public List<EmotionRecordResponseDto> getUserEmotions() {
        Long fakeUserId = 1L; // ★ 로그인 기능 없으므로 우선 고정된 사용자 ID 사용
        return emotionRecordService.getEmotionRecordsByUser(fakeUserId);
    }
*/
    // 등록 처리
    @PostMapping
    public String submitEntry(@ModelAttribute EmotionEntryDto dto) {
        emotionEntryService.save(dto);
        return "redirect:/calendar"; // 저장 후 감정 캘린더로 이동 (나중에 만들면 됨!)
    }
}
