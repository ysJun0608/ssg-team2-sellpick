package com.ssg.wsmt.member.controller;


import com.ssg.wsmt.member.dto.UserDTO;
import com.ssg.wsmt.member.service.CustomUserDetailsService;
import com.ssg.wsmt.member.service.JoinService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    private final JoinService joinService;
//    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    //관리자 페이지 라우팅
    @GetMapping("/admin")
    public String adminPage(){
        return "login/admin";
    }

    //로그인 페이지 라우팅
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
    //회원 가입 페이지 라우팅
    @GetMapping("/join")
    public String join() {
        return "login/join";
    }

    //회원 가입 완료 후 로그인 페이지 이동
    @PostMapping("/joinProc")
    public String joinProcess(UserDTO userDTO) {
        log.info(userDTO.getUsername());
        joinService.joinProcess(userDTO);
        return "redirect:/login/login";
    }


}

