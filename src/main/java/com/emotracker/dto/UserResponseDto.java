package com.emotracker.dto;

import com.emotracker.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
