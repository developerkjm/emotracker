// src/main/java/com/emotracker/dto/EmotionRecordRequestDto.java

package com.emotracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionRecordRequestDto {

    private String date; // ISO 문자열로 받을 거야 (예: "2025-04-14")
    private String emotionType; // 예: "HAPPY", "SAD"
    private String memo;
    private String imageUrl;

    private Long userId;  // 여기에 userId 추가
}
