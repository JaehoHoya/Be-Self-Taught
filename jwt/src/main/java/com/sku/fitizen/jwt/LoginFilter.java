package com.sku.fitizen.jwt;

import com.sku.fitizen.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

//UsernamePasswordAuthenticationFilter를 확장한 LoginFilter 클래스를 정의
public class LoginFilter extends UsernamePasswordAuthenticationFilter {



    private  final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        //클라이언트 요청에서 username, password 추출
        String userName = obtainUsername(req);
        String userPwd = obtainPassword(req);

        System.out.println("Attempting to authenticate userName: " + userName);
        System.out.println("Attempting to authenticate password: " + userPwd);
        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName, userPwd, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);

    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String userName= customUserDetails.getUsername(); // 사용자값 뽑음


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(userName, role, 3600000L); //토큰 발급 완료
        //Authorization: 타입 인증토큰 의 형태로 해야함
        response.addHeader("Authorization",token);

        Cookie cookie = new Cookie("Authorization",token);
        cookie.setHttpOnly(true); // 클라이언트 측에서 접근할 수 없도록 설정
        cookie.setSecure(true); // HTTPS에서만 쿠키 전송
        cookie.setPath("/"); // 애플리케이션의 모든 경로에서 쿠키 접근 가능
        cookie.setMaxAge(3600); // 쿠키 만료 시간 (초 단위)

        response.addCookie(cookie);

        response.sendRedirect("/");
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        //로그인 실패시 401 응답 코드 반환
        System.out.println("Unsuccessful");
        response.sendRedirect("/login?error=true");

    }

}
