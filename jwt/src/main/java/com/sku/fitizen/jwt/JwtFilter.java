package com.sku.fitizen.jwt;

import com.sku.fitizen.dto.CustomUserDetails;
import com.sku.fitizen.entity.FitizenUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//이 필터는 요청이 서버에 도착할 때마다 실행되며 JWT 토큰을 검증합니다.
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        // 쿠키에서 Authorization 값을 가져오기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    token ="Bearer "+cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.split(" ")[1];
        } else {
            filterChain.doFilter(request, response);
            return;
        }



        /*
        //request에서 Authorization 헤더를 찾음  키값을 가져옴
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
                                                //Bearer 로 시작하는지
            System.out.println("token null");
            filterChain.doFilter(request, response);
            // 필터 종료
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];
        */



        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {

            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 username과 role 획득
        String userName = jwtUtil.getUserName(token);
        String userRole = jwtUtil.getRole(token);

        //userEntity를 생성하여 값 set
        FitizenUser user = new FitizenUser();
        user.setUserName(userName);
        user.setUserPwd("임시 비번"); //비밀번호도 같이 초기화  굳이 디비에 접근해서 넣을 필요 없다
        user.setUserRole(userRole);

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}