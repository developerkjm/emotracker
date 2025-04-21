package com.emotracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmotionEntryDto {
    private String emotion;
    private String memo;
    private LocalDate date;
}
