package com.emotracker.dto;

import com.emotracker.domain.EmotionRecord;
import com.emotracker.domain.EmotionType;
import lombok.Getter;

@Getter
public class EmotionRecordResponseDto {
    private Long id;
    private String date;
    private String emotionType;
    private String emoji;
    private String imageFile;
    private String memo;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;

    public EmotionRecordResponseDto(EmotionRecord record) {
        EmotionType type = record.getEmotionType();

        this.id = record.getId();
        this.date = record.getDate().toString();
        this.emotionType = type.name();       // 예: "HAPPY"
        this.emoji = type.getEmoji();         // 예: "😊"
        this.imageFile = type.getImageFile(); // 예: "happy.png"
        this.memo = record.getMemo();
        this.imageUrl = record.getImageUrl();
        this.createdAt = record.getCreatedAt().toString();
        this.updatedAt = record.getUpdatedAt().toString();
    }
}
