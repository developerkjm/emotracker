package com.emotracker.dto;

import com.emotracker.domain.EmotionType;

public class EmotionResponseDto {
    private String emotionIcon;
    private String imageFile;

    public EmotionResponseDto(EmotionType emotionType) {
        this.emotionIcon = emotionType.getEmoji();
        this.imageFile = "/images/" + emotionType.getImageFile(); // 이미지 파일 경로 추가
    }

    public String getEmotionIcon() {
        return emotionIcon;
    }

    public String getImageFile() {
        return imageFile;
    }
}
