package com.emotracker.controller;

import com.emotracker.dto.UserRegisterDto;
import com.emotracker.dto.UserSignupRequestDto;
import com.emotracker.dto.UserLoginRequestDto;
import com.emotracker.dto.UserResponseDto;
import com.emotracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }
    // 회원가입
    @PostMapping("/signup")
    public String signup(@ModelAttribute UserRegisterDto dto) {
        userService.register(dto);
        return "redirect:/";
    }
    /*
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserSignupRequestDto dto) {
        return userService.signup(dto);
    }
    */

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

}
