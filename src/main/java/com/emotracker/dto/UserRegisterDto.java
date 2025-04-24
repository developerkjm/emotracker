package com.emotracker.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
}
