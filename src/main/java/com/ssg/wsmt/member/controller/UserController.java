package com.ssg.wsmt.member.controller;


import com.ssg.wsmt.member.dto.UserDTO;
import com.ssg.wsmt.member.exception.DuplicateUserException;
import com.ssg.wsmt.member.service.JoinService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    private final JoinService joinService;

    //관리자 페이지 라우팅
    @GetMapping("/admin")
    public String adminPage(){
        return "login/admin";
    }

    //로그인 페이지 라우팅
    @GetMapping("/login")
    public String login(@RequestParam(value = "error",required = false) String error, @RequestParam(value = "exception",required = false)String exception, Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
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
//    @PostMapping("/joinProc")
//    public String joinProcess(UserDTO userDTO) {
//        log.info(userDTO.getUsername());
//        try {
//            joinService.joinProcess(userDTO);
//            return "redirect:/login/login";
//        }catch (DuplicateUserException d){
//            d.printStackTrace();
//            return "redirect:/login/login";
//        }
//
//    }

    @PostMapping(value = "/joinProc", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> joinProcess(@ModelAttribute UserDTO userDTO) {
        log.info(userDTO.getUsername());
        try {
            joinService.joinProcess(userDTO);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (DuplicateUserException d) {
            return ResponseEntity.badRequest().body("이미 존재하는 회원입니다.");
        }
    }


}

