package com.emotracker.service;

import com.emotracker.domain.User;
import com.emotracker.dto.UserLoginRequestDto;
import com.emotracker.dto.UserRequestDto;
import com.emotracker.dto.UserResponseDto;
import com.emotracker.dto.UserSignupRequestDto;
import com.emotracker.repository.UserRepository;
import com.emotracker.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;


    public User saveUser(UserRequestDto userRequestDto) {
        // 1. 비밀번호 암호화 (여기서는 간단히 진행할 수 있음)
        String encodedPassword = userRequestDto.getPassword(); // 실제 서비스에서는 암호화 해야 함.

        // 2. User 엔티티로 변환
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .password(encodedPassword)
                .nickname(userRequestDto.getNickname())
                .build();

        // 3. User 객체 저장
        return userRepository.save(user);
    }

    public UserResponseDto signup(UserSignupRequestDto dto) {
        // 이메일 중복 확인
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("이미 등록된 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 유저 객체 생성 및 저장
        User user = User.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .username(dto.getUsername())
                .nickname(dto.getNickname())
                .role("USER")           // 기본 권한 USER
                .build();

        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }
    // 로그인
    public String login(UserLoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 비밀번호 확인
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail());  // JWT 토큰 반환
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
