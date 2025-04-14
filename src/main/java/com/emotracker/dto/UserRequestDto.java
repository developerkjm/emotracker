package com.emotracker.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private String nickname;
}
