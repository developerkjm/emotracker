package com.emotracker.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "your_secret_key";  // 비밀키는 환경변수나 외부 파일에서 관리하는 것이 좋다.

    // JWT 토큰 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간 유효
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT 토큰에서 사용자 정보 추출
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // JWT 토큰에서 특정 클레임 추출
    private <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // JWT 토큰에서 모든 클레임을 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰이 만료되었는지 확인
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰 만료 시간 추출
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    @FunctionalInterface
    private interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
