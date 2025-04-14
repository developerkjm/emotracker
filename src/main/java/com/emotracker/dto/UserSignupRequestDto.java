package com.emotracker.dto;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String username;
}
