package com.emotracker.controller;

import com.emotracker.dto.UserSignupRequestDto;
import com.emotracker.dto.UserLoginRequestDto;
import com.emotracker.dto.UserResponseDto;
import com.emotracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserSignupRequestDto dto) {
        return userService.signup(dto);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDto dto) {
        return userService.login(dto);
    }
}
