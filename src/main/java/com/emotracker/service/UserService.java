package com.emotracker.service;

import com.emotracker.domain.User;
import com.emotracker.dto.*;
import com.emotracker.repository.UserRepository;
import com.emotracker.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    //회원가입
    public void register(UserRegisterDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setNickname(dto.getNickname());
        user.setRole("USER");

        userRepository.save(user);

        // ✅ 자동 로그인 처리
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                );

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

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
