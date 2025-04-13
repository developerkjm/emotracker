// src/main/java/com/emotracker/domain/EmotionRecord.java

package com.emotracker.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EmotionType emotionType;

    private String memo;

    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ✅ 추가 부분 : user
    @ManyToOne(fetch = FetchType.LAZY) // 여러 감정 기록이 한 명의 유저에 속할 수 있다는 관계.
    @JoinColumn(name = "user_id") // 지연 로딩. user를 바로 가져오지 않고 나중에 필요할 때 가져옴. DB에서 user_id라는 컬럼으로 외래키 설정.
    private User user;

    // TODO: 추후에 User 추가 시 연관관계 매핑 예정
}
