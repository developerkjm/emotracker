package com.emotracker.config;

import com.emotracker.repository.UserRepository;
import com.emotracker.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API 서버이므로 CSRF 보호 비활성화 (웹 애플리케이션의 경우 활성화 권장)
                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/emotions/**", "/api/users/**",  "/api/posts", "/api/posts/**" , "/api/comments/**" , "/api/files/**").permitAll() // 해당 경로는 인증 없이 접근 가능
//                        .requestMatchers("/**").permitAll() // 해당 경로는 인증 없이 접근 가능
                        .requestMatchers("/", "/login", "/calendar", "/pdf1", "/api/posts/community", "/signup", "/css/**", "/js/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/emotion-entries").permitAll() // ✅ 요거 추가!
                        .requestMatchers("/api/emotion-entries/**").authenticated() // ✅ POST, PUT, DELETE 등은 여전히 인증 필요

                        .anyRequest().permitAll() // 그 외의 요청은 인증이 필요함
                )
                .formLogin(form -> form
                        .loginPage("/login")              // GET /login 은 login.html 연결됨
                        .defaultSuccessUrl("/", true)     // 로그인 성공 시 홈으로 이동
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")            // 로그아웃 성공 시 이동
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) // ✅ 여기!
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
