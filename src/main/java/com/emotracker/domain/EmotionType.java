// src/main/java/com/emotracker/domain/EmotionType.java

/*
public enum EmotionType {
    HAPPY, SAD, TIRED, SUCCESS, FAILURE,
    ANGRY, GRATEFUL, LOVE, EMPTY, RELAXED
}
 */
package com.emotracker.domain;

public enum EmotionType {
    HAPPY("😊", "happy.png"),
    SAD("😢", "sad.png"),
    ANGRY("😠", "angry.png"),
    NEUTRAL("😐", "neutral.png"),
    SURPRISED("😲", "surprised.png");

    private final String emoji;
    private final String imageFile;

    EmotionType(String emoji, String imageFile) {
        this.emoji = emoji;
        this.imageFile = imageFile;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getImageFile() {
        return imageFile;
    }
}

