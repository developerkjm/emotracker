// src/main/java/com/emotracker/dto/EmotionRecordResponseDto.java

package com.emotracker.dto;

import com.emotracker.domain.EmotionRecord;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionRecordResponseDto {
    private Long id;
    private String date;
    private String emotionType;
    private String memo;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;

    public EmotionRecordResponseDto(EmotionRecord record) {
        this.id = record.getId();
        this.date = record.getDate().toString();
        this.emotionType = record.getEmotionType().name();
        this.memo = record.getMemo();
        this.imageUrl = record.getImageUrl();
        this.createdAt = record.getCreatedAt().toString();
        this.updatedAt = record.getUpdatedAt().toString();
    }
}
