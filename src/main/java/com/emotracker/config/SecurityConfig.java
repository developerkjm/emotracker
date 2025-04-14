package com.emotracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API 서버이므로 CSRF 보호 비활성화 (웹 애플리케이션의 경우 활성화 권장)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/emotions/**", "/api/users/**").permitAll() // 해당 경로는 인증 없이 접근 가능
                        .anyRequest().authenticated() // 그 외의 요청은 인증이 필요함
                );

        return http.build();
    }
}
