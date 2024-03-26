package com.ssg.wsmt.member.config;

import com.ssg.wsmt.member.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;


//인가 작업 커스텀하는 클래스
//스프링 3점대라 WebSecurityConfigurerAdapter 상속 받지않음
@Configuration //springBoot의 설정 클래스로 등록
@EnableWebSecurity // 이 클래스가 스프링 시큐리티에서도 관리
@RequiredArgsConstructor
public class SecurityConfig {

    //해쉬 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //특정 경로에 따른 로직 작성, 상 단부터 실행, 순서 잘짜야함 (추후 수정)
        http
                .authorizeHttpRequests((auth) -> auth //3.1점대라 람다로 표현해야함(필수)
                        .requestMatchers("/assets/css/**","/images/**","/assets/jsReal/**","/assets/lib/**").permitAll()
                        .requestMatchers("/", "/login/login", "/login/join","/login/loginProc", "/login/joinProc", "/layout/**").permitAll() //루트 경로나 /login경로 또는 정적 파일들에 대한 작업 모든 사용자의 접근 허용
                        .requestMatchers("/login/admin").hasRole("ADMIN") //관리자 접근 가능
                        .requestMatchers("/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() //위에서 처리 못한 경로에 대한 처리,로그인한 사용자만 접근 가능
                );

        http
                .formLogin((auth) ->
                        auth.loginPage("/login/login")
                                .loginProcessingUrl("/login/loginProc")
                                .defaultSuccessUrl("/")
                                .failureUrl("/login/login?error=true")// 로그인 실패 후 다시 로그인 페이지로 이동
                                .permitAll() //로그인 페이지는 인증 없이 접근 가능
                );

        http
                .csrf((auth -> auth.disable()));

        http
                .logout((auth -> auth.logoutUrl("/logout") //로그아웃 처리 URL
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)// 세션 무효화
                ))

        ; //로그아웃 성공 시 해당 주소로 이동


//        http
//                .httpBasic(Customizer.withDefaults());

        http
                .sessionManagement((auth -> auth.maximumSessions(7).maxSessionsPreventsLogin(true)));

        http
                .sessionManagement((auth -> auth
                        .sessionFixation().changeSessionId()));

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스 spring security 대상에서 제외
        return (web) ->
                web
                        .ignoring()
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations()
                        );
    }


}