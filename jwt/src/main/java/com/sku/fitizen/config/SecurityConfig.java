package com.sku.fitizen.config;


import com.sku.fitizen.jwt.JwtFilter;
import com.sku.fitizen.jwt.JwtUtil;
import com.sku.fitizen.jwt.LoginFilter;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final  JwtUtil jwtUtil;
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration ,JwtUtil jwtUtil) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }



    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    //비밀번호를 해시화하여 보안성을 높이는 데 사용됨.
    //BCryptPasswordEncoder는 비밀번호를 암호화할 때 BCrypt 알고리즘을 사용됨.
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //@Bean :의존성 주입이 필요한 객체를 빈으로 등록하여 Spring IOC 컨테이너가 객체의 생성과
    //       의존성 주입을 관리하게 한다 -> 즉 개발자는 주입된 의존성을 사용하는데만 집중할 수 있음.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception
    {

        /*
         CSRF는 사용자 의지와는 상관없이 해커가 의도한 행위(수정, 삭제, 등록 등)를
               사용자 권한을 이용해 서버에 요청을 보내는 공격
         XSS는

         csrf disable : 세션방식이 아닌 jwt 방식이라 필요 없다 판단?
         토큰 기반 인증은 서버의 무상태성(Stateless)를 유지하기위한 시스템
         무상태성: 서버가 클라이언트의 상태를 보존하지 않음

        */
        http
               .csrf((auth)->auth.disable());

        //Form 로그인 방식 disable
        //UsernamePasswordAuthenticationFilter.class 라는 것을 사용할 수 있는데 이건 세션 방식에 맞춰져 있어서
        //커스텀 한다고 생각.
        http
                .formLogin((auth)->auth.disable());

        // http basic 인증 방식 disable
        http
                .httpBasic((auth)->auth.disable());

        // 경로별 인가 작업
        http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/", "/test","/login/**", "/join/**","/challenge/**").permitAll()
                        .requestMatchers("admin/**").hasRole("ADMIN")
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                        .anyRequest().authenticated()

                );


        http
                .addFilterBefore(new JwtFilter(jwtUtil),LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class);

        /*
           addFilter
           addFilterAt :원하는 자리에 등록
           addFilterBefore :해당하는 필터 전에
           addFilterAfter : 해당하는 필터 후에
        */

        //세션 설정
        http
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

}
