package com.sku.fitizen.jwt;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
//JWT 생성 및 검증을 담당하는 유틸리티 클래스
@Component
public class JwtUtil {

    private SecretKey secretKey;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret)
    {
       this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 검증을 진행할 3개의 메소드
    // verifyWith(secretKey)
    public String getUserName(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userName", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    // 토큰이 만료되었는지
    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }





     //로그인 성공시 토큰 생성하러옴
    // 토큰 생성 메소드
    public String createJwt(String userName, String role, Long expiredMs) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiredMs);

        System.out.println("JWT Creation Time: " + now);
        System.out.println("JWT Expiration Time: " + expirationDate);
        return Jwts.builder()
                .claim("userName", userName)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis())) // 현재 발행시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // 토큰 만료 시간
                .signWith(secretKey)  // 암호화 진행
                .compact(); // 토큰 리턴


    }


}

